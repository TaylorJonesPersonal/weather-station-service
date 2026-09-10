package com.fujita.weather_station_service.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.Instant;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Reading {

    private Instant createdAt = Instant.now();
    private float temperature;
    private int probeTemperature;
    private float relativeHumidity;
    private float barometricPressure;
    private int pulses;
    double calculatedVoltage;

}
