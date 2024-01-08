package com.pdabrowski.WeatherApp.rest;

import com.pdabrowski.WeatherApp.entity.Fall;
import com.pdabrowski.WeatherApp.entity.MeasurementStation;
import com.pdabrowski.WeatherApp.entity.Temperature;
import com.pdabrowski.WeatherApp.service.MeasurementStationService;
import com.pdabrowski.WeatherApp.service.TemperatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
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

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");
            LocalDateTime localDateTime = LocalDateTime.parse(data.get("time"), formatter);
            Instant time = localDateTime.toInstant(ZoneOffset.UTC);

            Temperature newTemperature = new Temperature();
            newTemperature.setTime(time);
            newTemperature.setTemperature(Double.parseDouble(data.get("temperature")));

            existingMeasurementStation.addTemperature(newTemperature);

            return temperatureService.saveTemperature(newTemperature);
        }

        return null;
    }
    @GetMapping("/day")
    public Map<Integer, Double> getData(@RequestParam String date, @RequestParam String cityId) throws ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse(date, formatter);
        LocalDateTime startDateTime = startDate.atStartOfDay();
        Instant startTime = startDateTime.toInstant(ZoneOffset.UTC);

        Integer city = Integer.parseInt(cityId);

        Map<Integer, Double> falls = this.temperatureService.getByDay(startTime, city).orElse(null);

        System.out.println(falls);

        return falls;
    }

    @GetMapping("/last")
    public Temperature getLast(@RequestParam String cityId){

        Integer city = Integer.parseInt(cityId);
        Temperature data = this.temperatureService.getLastFromCity(city).orElse(null);

        return data;
    }

}
