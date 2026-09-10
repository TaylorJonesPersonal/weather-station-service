package com.fujita.weather_station_service.Controllers;

import com.fujita.weather_station_service.DTO.CreateConditionResponse;
import com.fujita.weather_station_service.Model.Condition;
import com.fujita.weather_station_service.Model.Reading;
import com.fujita.weather_station_service.Service.ConditionService;
import com.fujita.weather_station_service.Service.ReadingCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/condition")
public class ConditionController {

    private final ReadingCollectionService readingCollectionService;
    private final ConditionService conditionService;

    @Autowired
    public ConditionController(ReadingCollectionService readingCollectionService, ConditionService conditionService) {
        this.readingCollectionService = readingCollectionService;
        this.conditionService = conditionService;
    }

    @PostMapping
    public ResponseEntity<CreateConditionResponse> getCurrentCondition() {
        Reading newReading = readingCollectionService.createReading();
        return new ResponseEntity<>(conditionService.createCondition(newReading), HttpStatus.CREATED);
    }
}
