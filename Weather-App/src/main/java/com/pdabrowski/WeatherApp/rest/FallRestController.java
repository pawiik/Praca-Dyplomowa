package com.pdabrowski.WeatherApp.rest;

import com.pdabrowski.WeatherApp.entity.Fall;
import com.pdabrowski.WeatherApp.entity.MeasurementStation;
import com.pdabrowski.WeatherApp.entity.Region;
import com.pdabrowski.WeatherApp.service.FallService;
import com.pdabrowski.WeatherApp.service.MeasurementStationService;
import com.pdabrowski.WeatherApp.service.RegionService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/fall")
public class FallRestController {

    FallService fallService;
    MeasurementStationService measurementStationService;
    RegionService regionService;


    @Autowired
    public FallRestController(FallService fallService,
                              MeasurementStationService measurementStationService,
                              RegionService regionService){
        this.fallService = fallService;
        this.measurementStationService = measurementStationService;
        this.regionService = regionService;
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
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime localDateTime = LocalDateTime.parse(data.get("time"), formatter);
            Instant time = localDateTime.toInstant(ZoneOffset.UTC);

            Fall newFall = new Fall();
            newFall.setTime(time);
            newFall.setTemperature(Double.parseDouble(data.get("fall")));

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

//        Region existingRegion = this.regionService.getRegionById(regionId);
//
//        if(existingRegion != null){

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
            LocalDateTime start = LocalDateTime.parse(data.get("startTime"), formatter);
            Instant startTime = start.toInstant(ZoneOffset.UTC);

            LocalDateTime end = LocalDateTime.parse(data.get("endTime"), formatter);
            Instant endTime = end.toInstant(ZoneOffset.UTC);
            System.out.println("start " + startTime);
            System.out.println("end " + endTime);
            List<Fall> falls = this.fallService.getByTimePeriod(startTime, endTime, regionId).orElse(null);
//            System.out.println(falls);
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
    public Map<Integer, Double> getData(@RequestParam String param1, @RequestParam String param2) throws ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse(param1, formatter);
        LocalDateTime startDateTime = startDate.atStartOfDay();
        Instant startTime = startDateTime.toInstant(ZoneOffset.UTC);

        Integer cityId = Integer.parseInt(param2);

        Map<Integer, Double> falls = this.fallService.getByDay(startTime, cityId).orElse(null);

        System.out.println(falls);

        return falls;
    }

    @GetMapping("/last")
    public Fall getLast(@RequestParam String cityId){

        Integer city = Integer.parseInt(cityId);
        Fall data = this.fallService.getLastFromCity(city).orElse(null);

        return data;
    }


}
