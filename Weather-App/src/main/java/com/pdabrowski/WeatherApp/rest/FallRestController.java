package com.pdabrowski.WeatherApp.rest;

import com.pdabrowski.WeatherApp.entity.City;
import com.pdabrowski.WeatherApp.entity.Fall;
import com.pdabrowski.WeatherApp.entity.MeasurementStation;
import com.pdabrowski.WeatherApp.service.CityService;
import com.pdabrowski.WeatherApp.service.FallService;
import com.pdabrowski.WeatherApp.service.MeasurementStationService;
import com.pdabrowski.WeatherApp.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/fall")
public class FallRestController {

    FallService fallService;
    MeasurementStationService measurementStationService;
    RegionService regionService;
    CityService cityService;


    @Autowired
    public FallRestController(FallService fallService,
                              MeasurementStationService measurementStationService,
                              RegionService regionService,
                              CityService cityService){
        this.fallService = fallService;
        this.measurementStationService = measurementStationService;
        this.regionService = regionService;
        this.cityService = cityService;
    }

    @GetMapping("/falls")
    public List<Fall> getAllFalls(){
        return fallService.getAllFalls();
    }

    @GetMapping("/{data}")
    public Fall getFallById( @PathVariable String data){
        return fallService.getFallById(Integer.parseInt(data)).orElse(null);
    }

    @PostMapping("/")
    public Fall addFall(@RequestBody Map<String, String> data){
        MeasurementStation existingMeasurementStation = measurementStationService.getStationById(Integer.parseInt(data.get("measurementStationId"))).orElse(null);

        if(existingMeasurementStation != null){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime localDateTime = LocalDateTime.parse(data.get("time"), formatter);
            Instant time = localDateTime.toInstant(ZoneOffset.UTC);

            Fall newFall = new Fall();
            newFall.setTime(time);
            newFall.setFall(Double.parseDouble(data.get("fall")));

            existingMeasurementStation.addFall(newFall);

            return fallService.saveFall(newFall);
        }

        return null;
    }

    @PostMapping("/time")
    public List<Fall> getByTimePeriod(@RequestBody Map<String, String> data){
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
            List<Fall> falls = this.fallService.getByTimePeriod(startTime, endTime, regionId).orElse(null);

            if(falls != null){

                return falls;
            }
            else{
                System.out.println("no falls");
                return null;
            }
//        }
//        else{
//            System.out.println("wrong region");
//            return null;
//        }
    }

    @GetMapping("/day")
    public Map<Integer, Double> getData(@RequestParam String date, @RequestParam String cityId) throws ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate;
        System.out.println("DATA");
        System.out.println(date);
        System.out.println(cityId);
        try {
            startDate = LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {
            throw new ParseException("Invalid date format. Expected format: yyyy-MM-dd", 0);
        }

        City existingCity;
        try {
            existingCity = this.cityService.getCityById(Integer.parseInt(cityId)).orElseThrow(() ->
                    new IllegalArgumentException("City not found with ID: " + cityId));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid city ID: " + cityId);
        }

        Map<Integer, Double> falls = this.fallService.getByDay(date, existingCity.getCityId())
                .orElseThrow(() -> new IllegalStateException("No data available for the specified day and city"));

        return falls;
    }

    @GetMapping("/last")
    public Fall getLast(@RequestParam String cityId){

        Integer city = Integer.parseInt(cityId);
        Fall data = this.fallService.getLastFromCity(city).orElse(null);

        return data;
    }


}
