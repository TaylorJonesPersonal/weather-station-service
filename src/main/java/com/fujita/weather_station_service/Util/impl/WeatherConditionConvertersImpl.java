package com.fujita.weather_station_service.Util.impl;

import com.fujita.weather_station_service.Constants.WindVaneConstants;
import com.fujita.weather_station_service.Util.WeatherConditionConverters;
import com.fujita.weather_station_service.Enums.Direction;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class WeatherConditionConvertersImpl implements WeatherConditionConverters {

    public float readingToMbar(float measurement) {
        return measurement / 100;
    }

    public float cToF(float celsius) {
        return (float)(celsius * 1.8) + 32;
    }

    public float fToC(float fahrenheit) {
        return (fahrenheit - 32) * 5/9;
    }

    public Optional<Direction> directionStringToDirection(String direction) {
        return Direction.byDisplayNameIgnoreCase(direction);
    }

    public double pulsesToWindSpeed(int pulses) {
        return pulses / 3.0 * 1.492;
    }

    public int calculatedVoltageToRawVaneValue(double calculatedVoltage) {
        int rawVaneValue = (int) Math.round((calculatedVoltage / 3.3) * 1023.0);
        return Math.min(1023, Math.max(0, rawVaneValue));
    }

    public String getWindDirection(int adcValue) {
        int closestIdx = 0;
        int minimumDifference = Integer.MAX_VALUE;

        for (int i = 0; i < WindVaneConstants.ADC_THRESHOLDS.length; i++) {
            int diff = Math.abs(adcValue - WindVaneConstants.ADC_THRESHOLDS[i]);
            if (diff < minimumDifference) {
                minimumDifference = diff;
                closestIdx = i;
            }
        }
        return WindVaneConstants.DIRECTIONS[closestIdx];
    }
}
