package com.fujita.weather_station_service.Enums;

import java.util.Arrays;
import java.util.Optional;

public enum Direction {
    NORTH("N"),
    NORTHNORTHEAST("NNE"),
    NORTHEAST("NE"),
    EASTNORTHEAST("ENE"),
    EAST("E"),
    EASTSOUTHEAST("ESE"),
    SOUTHEAST("SE"),
    SOUTHSOUTHEAST("SSE"),
    SOUTH("S"),
    SOUTHSOUTHWEST("SSW"),
    SOUTHWEST("SW"),
    WESTSOUTHWEST("WSW"),
    WEST("W"),
    WESTNORTHWEST("WNW"),
    NORTHWEST("NW"),
    NORTHNORTHWEST("NNW");

    private final String displayName;

    Direction(String displayName) {
        this.displayName = displayName;
    }

    public static Optional<Direction> byDisplayNameIgnoreCase(String givenName) {
        return Arrays.stream(values()).filter(it -> it.displayName.equalsIgnoreCase(givenName)).findAny();
    }

    @Override
    public String toString() {
        return this.displayName;
    }
}
