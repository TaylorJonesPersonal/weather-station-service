package com.fujita.weather_station_service.Repository;

import com.fujita.weather_station_service.Model.Condition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConditionRepository extends JpaRepository<Condition, Long> {
}
