package com.fujita.weather_station_service.Service.impl;

import com.fujita.weather_station_service.Model.Condition;
import com.fujita.weather_station_service.Model.Reading;
import com.fujita.weather_station_service.Repository.ConditionRepository;
import com.fujita.weather_station_service.Service.ConditionService;
import com.fujita.weather_station_service.Util.WeatherConditionConverters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConditionServiceImpl implements ConditionService {

    private final ConditionRepository conditionRepository;
    private final WeatherConditionConverters weatherConditionConverters;

    @Autowired
    public ConditionServiceImpl(ConditionRepository conditionRepository,
                                WeatherConditionConverters weatherConditionConverters) {
        this.conditionRepository = conditionRepository;
        this.weatherConditionConverters = weatherConditionConverters;
    }

    public Condition createCondition(Reading reading) {
        Condition newCondition = Condition
                .builder()
                .mBar(weatherConditionConverters.readingToMbar(reading.getBarometricPressure()))
                .temp_c(reading.getTemperature())
                .temp_f(weatherConditionConverters.cToF(reading.getTemperature()))
                .probe_temp_c((float) reading.getProbeTemperature() / 1000)
                .probe_temp_f(weatherConditionConverters.cToF((float) reading.getProbeTemperature()/1000))
                .relHumidityPercent(reading.getRelativeHumidity())
                .windSpeed(weatherConditionConverters.pulsesToWindSpeed(reading.getPulses()))
                .windDirection(weatherConditionConverters.directionStringToDirection(weatherConditionConverters.getWindDirection(weatherConditionConverters.calculatedVoltageToRawVaneValue(reading.getCalculatedVoltage()))).orElse(null))
                .build();
        conditionRepository.save(newCondition);
        return newCondition;
    }

}
