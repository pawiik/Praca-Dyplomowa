package com.pdabrowski.WeatherApp.rest;

import com.pdabrowski.WeatherApp.entity.MeasurementStation;
import com.pdabrowski.WeatherApp.entity.UV;
import com.pdabrowski.WeatherApp.entity.Wind;
import com.pdabrowski.WeatherApp.service.MeasurementStationService;
import com.pdabrowski.WeatherApp.service.WindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/wind")
public class WindRestController {

    WindService windService;
    MeasurementStationService measurementStationService;

    @Autowired
    public WindRestController(WindService windService, MeasurementStationService measurementStationService){
        this.windService = windService;
        this.measurementStationService = measurementStationService;
    }


    @GetMapping("/winds")
    public List<Wind> getAllUVs(){
        return windService.getAllWinds();
    }

    @GetMapping("/")
    public Wind getUVById(@RequestBody Map<String, Integer> data){
        return windService.getWindById(data.get("measurementId")).orElse(null);
    }

    @PostMapping("/")
    public Wind addTemperature(@RequestBody Map<String, String> data){
        MeasurementStation existingMeasurementStation = measurementStationService.getStationById(Integer.parseInt(data.get("measurementStationId"))).orElse(null);

        if(existingMeasurementStation != null){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");
            LocalDateTime localDateTime = LocalDateTime.parse(data.get("time"), formatter);
            Instant time = localDateTime.toInstant(ZoneOffset.UTC);

            Wind newWind = new Wind();
            newWind.setTime(time);
            newWind.setTemperature(Double.parseDouble(data.get("wind")));

            existingMeasurementStation.addWind(newWind);

            return windService.saveWind(newWind);
        }

        return null;
    }
}
