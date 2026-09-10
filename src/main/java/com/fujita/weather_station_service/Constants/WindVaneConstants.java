package com.fujita.weather_station_service.Constants;

public class WindVaneConstants {
    public static final int[] ADC_THRESHOLDS = {
            243, // N
            406, // NNE -> (Now rounds to N)
            564, // NE
            83,  // ENE -> (Now rounds to E)
            935, // E
            65,  // ESE -> (Now rounds to E)
            836, // SE
            126, // SSE -> (Now rounds to S)
            736, // S
            244, // SSW -> (Now rounds to S)
            395, // SW
            599, // WSW -> (Now rounds to W)
            76,  // W
            827, // WNW -> (Now rounds to W)
            134, // NW
            702  // NNW -> (Now rounds to N)
    };

    // Updated to filter out the third-degree specifications
    public static final String[] DIRECTIONS = {
            "N",  // N
            "N",  // NNE rounded down
            "NE", // NE
            "E",  // ENE rounded up
            "E",  // E
            "E",  // ESE rounded down
            "SE", // SE
            "S",  // SSE rounded up
            "S",  // S
            "S",  // SSW rounded down
            "SW", // SW
            "W",  // WSW rounded up
            "W",  // W
            "W",  // WNW rounded down
            "NW", // NW
            "N"   // NNW rounded up
    };
}
