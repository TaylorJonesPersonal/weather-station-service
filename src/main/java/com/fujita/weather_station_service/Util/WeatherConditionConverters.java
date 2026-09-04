package com.fujita.weather_station_service.Util;

import com.fujita.weather_station_service.Enums.Direction;

import java.util.Optional;

public interface WeatherConditionConverters {
    float readingToMbar(float measurement);
    float cToF(float celsius);
    float fToC(float fahrenheit);
    Optional<Direction> directionStringToDirection(String direction);
    double pulsesToWindSpeed(int pulses);
    String getWindDirection(int adcValue);
    int calculatedVoltageToRawVaneValue(double calculatedVoltage);
}
