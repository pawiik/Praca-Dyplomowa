package com.pdabrowski.WeatherApp.rest;

import com.pdabrowski.WeatherApp.entity.Fall;
import com.pdabrowski.WeatherApp.entity.MeasurementStation;
import com.pdabrowski.WeatherApp.entity.UV;
import com.pdabrowski.WeatherApp.entity.Wind;
import com.pdabrowski.WeatherApp.service.MeasurementStationService;
import com.pdabrowski.WeatherApp.service.WindService;
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

    @GetMapping("/day")
    public Map<Integer, Double> getData(@RequestParam String date, @RequestParam String cityId) throws ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse(date, formatter);
        LocalDateTime startDateTime = startDate.atStartOfDay();
        Instant startTime = startDateTime.toInstant(ZoneOffset.UTC);

        Integer city = Integer.parseInt(cityId);

        Map<Integer, Double> falls = this.windService.getByDay(startTime, city).orElse(null);

        System.out.println(falls);

        return falls;
    }

    @GetMapping("/last")
    public Wind getLast(@RequestParam String cityId){

        Integer city = Integer.parseInt(cityId);
        Wind data = this.windService.getLastFromCity(city).orElse(null);

        return data;
    }

    @PostMapping("/time")
    public List<Wind> getByTimePeriod(@RequestBody Map<String, String> data) {
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
        List<Wind> falls = this.windService.getByTimePeriod(startTime, endTime, regionId).orElse(null);

        if (falls != null) {

            return falls;
        } else {
            System.out.println("no falls");
            return null;
        }
    }
}
