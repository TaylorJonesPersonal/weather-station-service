package com.fujita.weather_station_service.Service.impl;

import com.fujita.weather_station_service.Model.Reading;
import com.fujita.weather_station_service.Service.CSVWriterService;
import de.siegmar.fastcsv.writer.CsvWriter;
import de.siegmar.fastcsv.writer.LineDelimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

@Service
public class CSVWriterImpl implements CSVWriterService {

    private final Path path;

    @Autowired
    public CSVWriterImpl(@Value("${storage.csv-path}") Path path) {
        this.path = path;
    }

    public synchronized void write(Reading reading) {
        try(CsvWriter csvWriter = CsvWriter.builder().fieldSeparator(',').lineDelimiter(LineDelimiter.LF).build(
                Files.newBufferedWriter(path, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.APPEND)
        )) {
            csvWriter.writeRecord(
                    String.valueOf(reading.getId()),
                    String.valueOf(reading.getCalculatedVoltage()),
                    String.valueOf(reading.getPulses()),
                    String.valueOf(reading.getTemperature()),
                    String.valueOf(reading.getBarometricPressure()),
                    String.valueOf(reading.getRelativeHumidity()),
                    String.valueOf(reading.getCreatedAt()),
                    String.valueOf(reading.getUpdatedAt())
            );
        } catch(IOException e) {
            System.out.println(e);
        }
    }
}
