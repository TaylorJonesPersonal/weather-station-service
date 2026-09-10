package com.fujita.weather_station_service.Service;

import com.fujita.weather_station_service.Model.Reading;

public interface ReadingCollectionService {
    Reading createReading();
    int getProbeTemperature();
}
