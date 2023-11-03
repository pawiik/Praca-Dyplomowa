package com.pdabrowski.WeatherApp.rest;

import com.pdabrowski.WeatherApp.entity.Fall;
import com.pdabrowski.WeatherApp.entity.MeasurementStation;
import com.pdabrowski.WeatherApp.entity.Temperature;
import com.pdabrowski.WeatherApp.service.MeasurementStationService;
import com.pdabrowski.WeatherApp.service.TemperatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/temperature")
public class TemperatureRestController {

    TemperatureService temperatureService;
    MeasurementStationService measurementStationService;

    @Autowired
    public TemperatureRestController(TemperatureService temperatureService, MeasurementStationService measurementStationService){
        this.temperatureService = temperatureService;
        this.measurementStationService = measurementStationService;
    }

    @GetMapping("/temperatures")
    public List<Temperature> getAllFalls(){
        return temperatureService.getAllTemperatures();
    }

    @GetMapping("/")
    public Temperature getFallById(@RequestBody Map<String, Integer> data){
        return temperatureService.getTemperatureById(data.get("measurementId")).orElse(null);
    }

    @PostMapping("/")
    public Temperature addTemperature(@RequestBody Map<String, String> data){
        MeasurementStation existingMeasurementStation = measurementStationService.getStationById(Integer.parseInt(data.get("measurementStationId"))).orElse(null);

        if(existingMeasurementStation != null){

            Temperature newTemperature = new Temperature();
            newTemperature.setTime(Integer.parseInt(data.get("time")));
            newTemperature.setTemperature(Double.parseDouble(data.get("temperature")));

            existingMeasurementStation.addTemperature(newTemperature);

            return temperatureService.saveTemperature(newTemperature);
        }

        return null;
    }

}
