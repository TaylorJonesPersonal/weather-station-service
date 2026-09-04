package com.fujita.weather_station_service.Model;

import com.fujita.weather_station_service.Enums.Direction;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name="conditions")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Condition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    private float mBar;
    private float temp_c;
    private float temp_f;
    private float probe_temp_c;
    private float probe_temp_f;
    private float relHumidityPercent;
    private Direction windDirection;
    private double windSpeed;
}
