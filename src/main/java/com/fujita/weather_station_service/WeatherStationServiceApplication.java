package com.fujita.weather_station_service;

import com.diozero.api.*;
import com.fujita.weather_station_service.Service.CSVWriterService;
import com.fujita.weather_station_service.Service.ReadingCollectionService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.pi4j.io.gpio.digital.*;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class WeatherStationServiceApplication {


    public static void main(String[] args) throws InterruptedException {

        ApplicationContext context = SpringApplication.run(WeatherStationServiceApplication.class, args);

        ReadingCollectionService readingCollectionService = context.getBean(ReadingCollectionService.class);
        CSVWriterService csvService = context.getBean(CSVWriterService.class);
        for(int i = 0; i < 10; i++) {
            Thread.sleep(3000);
            readingCollectionService.createReading();
        }
    }
}
