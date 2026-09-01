package com.fujita.weather_station_service;

import com.diozero.api.*;
import com.fujita.weather_station_service.Service.CSVWriterService;
import com.fujita.weather_station_service.Service.ReadingCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.concurrent.atomic.AtomicInteger;
import com.pi4j.Pi4J;
import com.pi4j.context.Context;
import com.pi4j.drivers.sensor.environment.bmx280.Bmx280Driver;
import com.pi4j.drivers.sensor.environment.bmx280.Bmx280Driver.Measurement;
import com.pi4j.io.gpio.digital.*;
import com.pi4j.io.i2c.I2C;
import com.pi4j.io.i2c.I2CConfig;
import com.pi4j.plugin.gpiod.provider.gpio.digital.GpioDDigitalInputProvider;
import com.pi4j.plugin.gpiod.provider.gpio.digital.GpioDDigitalOutputProvider;
import com.pi4j.plugin.linuxfs.provider.i2c.LinuxFsI2CProvider;
import com.pi4j.util.Console;
import com.fujita.weather_station_service.Constants.WindVaneConstants;
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
