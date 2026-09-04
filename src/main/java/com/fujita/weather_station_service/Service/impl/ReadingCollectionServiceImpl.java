    package com.fujita.weather_station_service.Service.impl;

    import com.fujita.weather_station_service.Service.ConditionService;
    import com.fujita.weather_station_service.Util.WeatherConditionConverters;
    import com.diozero.api.DigitalInputDevice;
    import com.fujita.weather_station_service.Constants.ScriptConstants;
    import com.fujita.weather_station_service.Model.Reading;
    import com.fujita.weather_station_service.Service.CSVWriterService;
    import com.fujita.weather_station_service.Service.ReadingCollectionService;
    import com.pi4j.drivers.sensor.environment.bmx280.Bmx280Driver;
    import com.pi4j.drivers.sensor.environment.bmx280.Bmx280Driver.Measurement;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.text.DecimalFormat;
    import java.util.concurrent.atomic.AtomicInteger;

    @Service
    public class ReadingCollectionServiceImpl implements ReadingCollectionService {

        private static final AtomicInteger rotationCount = new AtomicInteger(0);
        private final Bmx280Driver bme280;
        private final DigitalInputDevice anemometer;
        private final CSVWriterService csvWriter;
        private final ScriptConstants scriptConstants;
        private final WeatherConditionConverters weatherConditionConverters;
        private final ConditionService conditionService;

        @Autowired
        public ReadingCollectionServiceImpl(Bmx280Driver bme280,
                                            DigitalInputDevice anemometer,
                                            CSVWriterService csvWriter,
                                            ScriptConstants scriptConstants,
                                            WeatherConditionConverters weatherConditionConverters,
                                            ConditionService conditionService) {
            this.bme280 = bme280;
            this.anemometer = anemometer;
            this.csvWriter = csvWriter;
            this.scriptConstants = scriptConstants;
            this.weatherConditionConverters = weatherConditionConverters;
            this.conditionService = conditionService;
        }

        public void createReading() {
            System.out.println("--- Starting Weather Station Sensor Initialization ---");
            DecimalFormat df = new DecimalFormat("0.#");
            System.out.println("Initializing Anemometer interrupt and Python SPI telemetry link...");
            Reading newReading = new Reading();

                    anemometer.whenActivated((event) -> rotationCount.incrementAndGet());
                    System.out.println("Anemometer reading loop active.");

                    // Inline script that reads Channel 0 from the MCP3008 and prints ONLY the raw voltage float value

                    System.out.println("Wind Vane loop started. Press Ctrl+C to stop.");

                        Measurement measurement = bme280.readMeasurement();
                        System.out.println("Temperature " + df.format(weatherConditionConverters.cToF(measurement.getTemperature())) + " °F");
                        System.out.println("Barometric Pressure " + df.format(weatherConditionConverters.readingToMbar(measurement.getPressure())) + " mbar");
                        System.out.println("Relative Humidity " + df.format(measurement.getHumidity()) + "%");

                        newReading.setTemperature(measurement.getTemperature());
                        newReading.setBarometricPressure(measurement.getPressure());
                        newReading.setRelativeHumidity(measurement.getHumidity());
                        newReading.setPulses(rotationCount.get());
                        // Process Anemometer calculations
                        int pulses = rotationCount.getAndSet(0);
                        double windSpeedMph = weatherConditionConverters.pulsesToWindSpeed(pulses);
                        System.out.printf("Pulses: %d | Wind Speed: %.2f mph\n", pulses, windSpeedMph);

                        // Execute the working Python hardware block and read its output stream
                        double calculatedVoltage = 0.0;
                        try {
                            Process process = Runtime.getRuntime().exec(new String[]{"python3", "-c", ScriptConstants.readMCP3008Script});
                            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                                String outputLine = reader.readLine();
                                if (outputLine != null && !outputLine.trim().isEmpty()) {
                                    calculatedVoltage = Double.parseDouble(outputLine.trim());
                                }
                            }
                            process.waitFor();
                        } catch (Exception processEx) {
                            System.err.println("Failed to fetch data from Python SPI bridge: " + processEx.getMessage());
                        }

                        int probeTemperature = getProbeTemperature();
                        System.out.println("Probe Temperature: " + weatherConditionConverters.cToF((float) probeTemperature /1000));
                        newReading.setProbeTemperature(probeTemperature);

                        // Map the returned voltage safely back to our 10-bit integer scale
                        int rawVaneValue = (int) Math.round((calculatedVoltage / 3.3) * 1023.0);
                        // Clamp boundaries to prevent array index overflow faults
                        rawVaneValue = Math.min(1023, Math.max(0, rawVaneValue));

                        String direction = weatherConditionConverters.getWindDirection(rawVaneValue);

                        System.out.printf("Raw ADC: %4d | Voltage: %.2fV | Direction: %s\n", rawVaneValue, calculatedVoltage, weatherConditionConverters.directionStringToDirection(direction).orElse(null));
                        newReading.setCalculatedVoltage(calculatedVoltage);
                        csvWriter.write(newReading);
                        conditionService.createCondition(newReading);
        }

        @Override
        public int getProbeTemperature() {
            int probeTemperature = 0;
            try {
                Process process = Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", scriptConstants.getReadDS18B20Script()});
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                    String outputLine = reader.readLine();
                    if (outputLine != null && !outputLine.trim().isEmpty()) {
                        probeTemperature = Integer.parseInt(outputLine.trim());
                    }
                }
                return probeTemperature;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
