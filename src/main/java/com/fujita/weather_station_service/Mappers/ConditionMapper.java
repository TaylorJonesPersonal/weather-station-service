package com.fujita.weather_station_service.Mappers;

import com.fujita.weather_station_service.DTO.CreateConditionResponse;
import com.fujita.weather_station_service.Enums.Direction;
import com.fujita.weather_station_service.Model.Condition;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.text.DecimalFormat;

@Mapper(componentModel = "spring")
public interface ConditionMapper {
    @Mapping(target="barometricPressure", source="condition.barometricPressure", qualifiedByName = "barometricPressureToStringFormat")
    @Mapping(target="temperatureCelsius", source="condition.temp_c", qualifiedByName = "tempCToStringFormat")
    @Mapping(target="temperatureFahrenheit", source="condition.temp_f", qualifiedByName = "tempFToStringFormat")
    @Mapping(target="probeTemperatureCelsius", source="condition.probe_temp_c", qualifiedByName = "tempCToStringFormat")
    @Mapping(target="probeTemperatureFahrenheit", source="condition.probe_temp_f", qualifiedByName = "tempFToStringFormat")
    @Mapping(target="relativeHumidity", source="condition.relHumidityPercent", qualifiedByName = "relHumidityToStringFormat")
    @Mapping(target="windDirection", source="condition.windDirection", qualifiedByName = "directionToStringFormat")
    @Mapping(target="windSpeed", source="condition.windSpeed", qualifiedByName = "windSpeedToStringFormat")
    CreateConditionResponse conditionToCreateConditionResponse(Condition condition);

    @Named("barometricPressureToStringFormat")
    default String mBarToStringFormat(Float mBar) {
        DecimalFormat df = new DecimalFormat("####.##");
        return df.format(mBar).concat(" mbar");
    }

    @Named("tempCToStringFormat")
    default String tempCToStringFormat(Float temp) {
        DecimalFormat df = new DecimalFormat("##.##");
        return df.format(temp).concat("°C");
    }

    @Named("tempFToStringFormat")
    default String tempFToStringFormat(Float temp) {
        DecimalFormat df = new DecimalFormat("##.##");
        return df.format(temp).concat("°F");
    }

    @Named("directionToStringFormat")
    default String directionToStringFormat(Direction direction) {
        return direction.toString();
    }

    @Named("windSpeedToStringFormat")
    default String windSpeedToStringFormat(double windSpeed) {
        DecimalFormat df = new DecimalFormat("###.##");
        return df.format(windSpeed) + " mph";
    }

    @Named("relHumidityToStringFormat")
    default String relHumidityToStringFormat(Float relHumidity) {
        DecimalFormat df = new DecimalFormat("##.##");
        return df.format(relHumidity) + "%";
    }

}
