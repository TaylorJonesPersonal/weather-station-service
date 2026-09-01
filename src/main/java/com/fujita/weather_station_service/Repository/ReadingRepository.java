package com.fujita.weather_station_service.Repository;

import com.fujita.weather_station_service.Model.Reading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReadingRepository extends JpaRepository<Reading, Long> {
}
