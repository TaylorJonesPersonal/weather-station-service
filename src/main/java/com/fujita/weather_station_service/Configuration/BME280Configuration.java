package com.fujita.weather_station_service.Configuration;

import com.pi4j.Pi4J;
import com.pi4j.context.Context;
import com.pi4j.drivers.sensor.environment.bmx280.Bmx280Driver;
import com.pi4j.io.i2c.I2C;
import com.pi4j.io.i2c.I2CConfig;
import com.pi4j.plugin.gpiod.provider.gpio.digital.GpioDDigitalInputProvider;
import com.pi4j.plugin.gpiod.provider.gpio.digital.GpioDDigitalOutputProvider;
import com.pi4j.plugin.linuxfs.provider.i2c.LinuxFsI2CProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.fujita.weather_station_service.Constants.BME280Constants;

@Configuration
public class BME280Configuration {

    // 1. Core I2C Communication Layer for BME280
    // FIX 1: Use ONE manual context for both I2C and GPIO to bypass Spring's classloader issue
    @Bean(destroyMethod = "shutdown")
    public Context pi4JContext() {
        return Pi4J.newContextBuilder()
                .add(GpioDDigitalInputProvider.newInstance(),
                        GpioDDigitalOutputProvider.newInstance(),
                        LinuxFsI2CProvider.newInstance())
                .build();
    }

    @Bean
    public I2CConfig BME280Config(Context context) {
        return I2C.newConfigBuilder(context)
                .id("BME280")
                .bus(BME280Constants.I2C_BUS)
                .device(BME280Constants.I2C_ADDRESS)
                .build();
    }

    @Bean
    public I2C bme280Device(Context context) {
        return context.create(BME280Config(context));
    }

    @Bean
    public Bmx280Driver bmx280Driver(I2C bme280Device) {
        return new Bmx280Driver(bme280Device);
    }
}
