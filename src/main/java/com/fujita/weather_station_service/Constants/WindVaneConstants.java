package com.fujita.weather_station_service.Constants;

public class WindVaneConstants {
    public static final int[] ADC_THRESHOLDS = {
            961, // N    (Target: ~3.100V)
            630, // NNE  (Target: ~2.032V)
            780, // NE   (Target: ~2.516V)
            737, // ENE  (Target: ~2.377V)
            395, // E    (Target: ~1.274V)
            426, // ESE  (Target: ~1.374V)
            461, // SE   (Target: ~1.486V)
            531, // SSE  (Target: ~1.712V)
            287, // S    (Target: ~0.926V)
            489, // SSW  (Target: ~1.579V)
            196, // SW   (Target: ~0.632V)
            234, // WSW  (Target: ~0.755V)
            80,  // W    (Target: ~0.258V)
            423, // WNW  (Target: ~1.365V)
            127, // NW   (Target: ~0.411V)
            886  // NNW  (Target: ~2.857V)
    };

    public static final String[] DIRECTIONS = {
            "N", "NNE", "NE", "ENE", "E", "ESE", "SE", "SSE",
            "S", "SSW", "SW", "WSW", "W", "WNW", "NW", "NNW"
    };
}
