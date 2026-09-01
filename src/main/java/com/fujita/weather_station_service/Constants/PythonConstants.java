package com.fujita.weather_station_service.Constants;

public class PythonConstants {
    // Inline script that reads Channel 0 from the MCP3008 and prints ONLY the raw voltage float value
    public static final String readMCP3008Script = "import busio, digitalio, board; " +
                        "import adafruit_mcp3xxx.mcp3008 as MCP; " +
                        "from adafruit_mcp3xxx.analog_in import AnalogIn; " +
                        "spi = busio.SPI(board.SCLK, board.MOSI, board.MISO); " +
                        "cs = digitalio.DigitalInOut(board.D26); " +
                        "mcp = MCP.MCP3008(spi, cs); " +
                        "chan = AnalogIn(mcp, MCP.P0); " +
                        "print(f'{chan.voltage:.4f}')";
}
