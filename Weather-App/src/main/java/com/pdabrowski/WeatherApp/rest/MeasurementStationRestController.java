package com.pdabrowski.WeatherApp.rest;

import com.pdabrowski.WeatherApp.service.MeasurementStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/measurementStation")
public class MeasurementStationRestController {

    MeasurementStationService measurementStationService;

    @Autowired
    public MeasurementStationRestController(MeasurementStationService measurementStationService){
        this.measurementStationService = measurementStationService;
    }

}
