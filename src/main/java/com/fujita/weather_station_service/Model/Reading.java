package com.fujita.weather_station_service.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.Instant;

@Entity
@Table(name = "readings")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Reading {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Instant createdAt = Instant.now();
    private float temperature;
    private int probeTemperature;
    private float relativeHumidity;
    private float barometricPressure;
    private int pulses;
    double calculatedVoltage;

}
