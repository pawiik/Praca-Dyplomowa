package com.pdabrowski.WeatherApp.rest;

import com.pdabrowski.WeatherApp.entity.Humidity;
import com.pdabrowski.WeatherApp.entity.MeasurementStation;
import com.pdabrowski.WeatherApp.service.HumidityService;
import com.pdabrowski.WeatherApp.service.MeasurementStationService;
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
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime localDateTime = LocalDateTime.parse(data.get("time"), formatter);
            Instant time = localDateTime.toInstant(ZoneOffset.UTC);

            Humidity newHumidity = new Humidity();
            newHumidity.setTime(time);
            newHumidity.setHumidity(Double.parseDouble(data.get("humidity")));

            existingMeasurementStation.addHumidity(newHumidity);

            return humidityService.saveHumidity(newHumidity);
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

        Map<Integer, Double> falls = this.humidityService.getByDay(startTime, city).orElse(null);

        System.out.println(falls);

        return falls;
    }

    @GetMapping("/last")
    public Humidity getLast(@RequestParam String cityId){

        Integer city = Integer.parseInt(cityId);
        Humidity data = this.humidityService.getLastFromCity(city).orElse(null);

        return data;
    }

    @PostMapping("/time")
    public List<Humidity> getByTimePeriod(@RequestBody Map<String, String> data) {
        System.out.println("here i am");
        System.out.println();
        System.out.println(data.get("regionId"));
        System.out.println("a");
        Integer regionId = Integer.parseInt(data.get("regionId"));


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime start = LocalDateTime.parse(data.get("startTime"), formatter);
        Instant startTime = start.toInstant(ZoneOffset.UTC);

        LocalDateTime end = LocalDateTime.parse(data.get("endTime"), formatter);
        Instant endTime = end.toInstant(ZoneOffset.UTC);
        System.out.println("start " + startTime);
        System.out.println("end " + endTime);
        List<Humidity> falls = this.humidityService.getByTimePeriod(startTime, endTime, regionId).orElse(null);

        if (falls != null) {

            return falls;
        } else {
            System.out.println("no falls");
            return null;
        }
    }
}
