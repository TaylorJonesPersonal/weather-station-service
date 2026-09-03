package com.fujita.weather_station_service.Constants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ScriptConstants {
    private final String readDS18B20Script;
    private final String readDS18B20Execution = "cat w1_slave | grep -oP 't=\\K.*'";

    @Autowired
    public ScriptConstants(@Value("${storage.ds18B20path}") String ds18B20Location) {
        this.readDS18B20Script = "cd " + ds18B20Location + " && " + readDS18B20Execution;
    }

    // Inline script that reads Channel 0 from the MCP3008 and prints ONLY the raw voltage float value
    public static String readMCP3008Script = "import busio, digitalio, board; " +
                        "import adafruit_mcp3xxx.mcp3008 as MCP; " +
                        "from adafruit_mcp3xxx.analog_in import AnalogIn; " +
                        "spi = busio.SPI(board.SCLK, board.MOSI, board.MISO); " +
                        "cs = digitalio.DigitalInOut(board.D26); " +
                        "mcp = MCP.MCP3008(spi, cs); " +
                        "chan = AnalogIn(mcp, MCP.P0); " +
                        "print(f'{chan.voltage:.4f}')";

    public String getReadDS18B20Script() {
        return this.readDS18B20Script;
    }

}
