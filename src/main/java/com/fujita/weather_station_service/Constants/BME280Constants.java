package com.fujita.weather_station_service.Constants;

import com.pi4j.util.Console;
import java.util.concurrent.atomic.AtomicInteger;

public class BME280Constants {

    public static final Console console = new Console();
    public static final int I2C_BUS = 1;
    public static final int I2C_ADDRESS = 0x77;
    public static final int ANEMOMETER_PIN = 22;
    public static final AtomicInteger rotationCount = new AtomicInteger(0);
}
