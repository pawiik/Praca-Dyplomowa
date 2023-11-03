package com.pdabrowski.WeatherApp.rest;

import com.pdabrowski.WeatherApp.entity.Fall;
import com.pdabrowski.WeatherApp.entity.Humidity;
import com.pdabrowski.WeatherApp.entity.MeasurementStation;
import com.pdabrowski.WeatherApp.service.HumidityService;
import com.pdabrowski.WeatherApp.service.MeasurementStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/humidity")
public class HumidityRestController {

    HumidityService humidityService;
    MeasurementStationService measurementStationService;

    @Autowired
    public HumidityRestController(HumidityService humidityService, MeasurementStationService measurementStationService){
        this.humidityService = humidityService;
        this.measurementStationService = measurementStationService;
    }


    @GetMapping("/humidities")
    public List<Humidity> getAllFalls(){
        return humidityService.getAllHumidities();
    }

    @GetMapping("/")
    public Humidity getFallById(@RequestBody Map<String, Integer> data){
        return humidityService.getHumidityById(data.get("humidityId")).orElse(null);
    }

    @PostMapping("/")
    public Humidity addHumidity(@RequestBody Map<String, String> data){
        MeasurementStation existingMeasurementStation = measurementStationService.getStationById(Integer.parseInt(data.get("measurementStationId"))).orElse(null);

        if(existingMeasurementStation != null){

            Humidity newHumidity = new Humidity();
            newHumidity.setTime(Integer.parseInt(data.get("time")));
            newHumidity.setTemperature(Double.parseDouble(data.get("humidity")));

            existingMeasurementStation.addHumidity(newHumidity);

            return humidityService.saveHumidity(newHumidity);
        }

        return null;
    }
}
