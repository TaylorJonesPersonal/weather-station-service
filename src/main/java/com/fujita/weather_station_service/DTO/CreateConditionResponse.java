package com.fujita.weather_station_service.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateConditionResponse {
    private String barometricPressure;
    private String temperatureCelsius;
    private String temperatureFahrenheit;
    private String probeTemperatureCelsius;
    private String probeTemperatureFahrenheit;
    private String relativeHumidity;
    private String windDirection;
    private String windSpeed;
}
