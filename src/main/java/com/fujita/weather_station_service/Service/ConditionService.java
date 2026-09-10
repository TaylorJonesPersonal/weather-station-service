package com.fujita.weather_station_service.Service;

import com.fujita.weather_station_service.DTO.CreateConditionResponse;
import com.fujita.weather_station_service.Model.Reading;

public interface ConditionService {
    CreateConditionResponse createCondition(Reading reading);
}
