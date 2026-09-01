package com.fujita.weather_station_service.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.concurrent.atomic.AtomicInteger;

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

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    private float temperature;
    private float relativeHumidity;
    private float barometricPressure;
    private int pulses;
    double calculatedVoltage;

}
