package com.fujita.weather_station_service.Service;

import com.fujita.weather_station_service.Model.Condition;
import com.fujita.weather_station_service.Model.Reading;

public interface ConditionService {
    Condition createCondition(Reading reading);
}
