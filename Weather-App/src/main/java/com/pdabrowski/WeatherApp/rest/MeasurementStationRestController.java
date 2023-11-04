package com.pdabrowski.WeatherApp.rest;

import com.pdabrowski.WeatherApp.entity.City;
import com.pdabrowski.WeatherApp.entity.MeasurementStation;
import com.pdabrowski.WeatherApp.service.CityService;
import com.pdabrowski.WeatherApp.service.MeasurementStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/measurementStation")
public class MeasurementStationRestController {

    MeasurementStationService measurementStationService;
    CityService cityService;

    @Autowired
    public MeasurementStationRestController(MeasurementStationService measurementStationService, CityService cityService){
        this.measurementStationService = measurementStationService;
        this.cityService = cityService;
    }

    @GetMapping("/stations")
    public List<MeasurementStation> getAllStations(){
        return this.measurementStationService.getAllStations();
    }

    @GetMapping("/")
    public MeasurementStation getStationById(@RequestBody Map<String, Integer> data){
        return this.measurementStationService.getStationById(data.get("measurementStationId")).orElse(null);
    }

    @PostMapping("/")
    public MeasurementStation addMeasurementStation(@RequestBody Map<String, String> data){
        City existingCity = cityService.getCityById(Integer.parseInt(data.get("cityId"))).orElse(null);

        if(existingCity != null){
            MeasurementStation newStation = new MeasurementStation();
            newStation.setAddress(data.get("address"));
            newStation.setRegionId(1);

            existingCity.addMeasurementStation(newStation);

            return measurementStationService.saveStation(newStation);
        }

        return null;
    }

}
