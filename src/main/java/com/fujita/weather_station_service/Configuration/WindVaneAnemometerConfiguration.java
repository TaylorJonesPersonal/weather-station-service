package com.fujita.weather_station_service.Configuration;

import com.diozero.api.DigitalInputDevice;
import com.diozero.api.GpioEventTrigger;
import com.diozero.api.GpioPullUpDown;
import com.fujita.weather_station_service.Constants.BME280Constants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WindVaneAnemometerConfiguration {
    @Bean
    public DigitalInputDevice anemometer() {
        return new DigitalInputDevice(BME280Constants.ANEMOMETER_PIN, GpioPullUpDown.PULL_UP, GpioEventTrigger.BOTH);
    }
}
