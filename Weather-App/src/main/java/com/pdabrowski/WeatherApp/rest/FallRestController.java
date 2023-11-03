package com.pdabrowski.WeatherApp.rest;

import com.pdabrowski.WeatherApp.entity.Fall;
import com.pdabrowski.WeatherApp.entity.MeasurementStation;
import com.pdabrowski.WeatherApp.service.FallService;
import com.pdabrowski.WeatherApp.service.MeasurementStationService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/fall")
public class FallRestController {

    FallService fallService;
    MeasurementStationService measurementStationService;

    @Autowired
    public FallRestController(FallService fallService, MeasurementStationService measurementStationService){
        this.fallService = fallService;
        this.measurementStationService = measurementStationService;    }

    @GetMapping("/falls")
    public List<Fall> getAllFalls(){
        return fallService.getAllFalls();
    }

    @GetMapping("/")
    public Fall getFallById(@RequestBody Map<String, Integer> data){
        return fallService.getFallById(data.get("fallId")).orElse(null);
    }

    @PostMapping("/")
    public Fall addFall(@RequestBody Map<String, String> data){
        MeasurementStation existingMeasurementStation = measurementStationService.getStationById(Integer.parseInt(data.get("measurementStationId"))).orElse(null);

        if(existingMeasurementStation != null){

            Fall newFall = new Fall();
            newFall.setTime(Integer.parseInt(data.get("time")));
            newFall.setTemperature(Double.parseDouble(data.get("fall")));

            existingMeasurementStation.addFall(newFall);

            return fallService.saveFall(newFall);
        }

        return null;
    }


}
