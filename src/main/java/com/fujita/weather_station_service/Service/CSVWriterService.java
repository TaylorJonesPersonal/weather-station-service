package com.fujita.weather_station_service.Service;

import com.fujita.weather_station_service.Model.Reading;

import java.util.List;

public interface CSVWriterService {
    void write(Reading reading);
}
