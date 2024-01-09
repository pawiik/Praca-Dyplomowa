package com.pdabrowski.WeatherApp.rest;

import com.pdabrowski.WeatherApp.entity.Humidity;
import com.pdabrowski.WeatherApp.entity.MeasurementStation;
import com.pdabrowski.WeatherApp.entity.Temperature;
import com.pdabrowski.WeatherApp.entity.UV;
import com.pdabrowski.WeatherApp.service.MeasurementStationService;
import com.pdabrowski.WeatherApp.service.UVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("/uv")
public class UVRestController {

    UVService uvService;
    MeasurementStationService measurementStationService;

    @Autowired
    public UVRestController(UVService uvService, MeasurementStationService measurementStationService){
        this.uvService = uvService;
        this.measurementStationService = measurementStationService;
    }

    @GetMapping("/uvs")
    public List<UV> getAllUVs(){
        return uvService.getAllUVs();
    }

    @GetMapping("/")
    public UV getUVById(@RequestBody Map<String, Integer> data){
        return uvService.getUVById(data.get("measurementId")).orElse(null);
    }

    @PreAuthorize("hasRole('ROLE_employee')")
    @PostMapping("/")
    public UV addUV(@RequestBody Map<String, String> data){
        MeasurementStation existingMeasurementStation = measurementStationService.getStationById(Integer.parseInt(data.get("measurementStationId"))).orElse(null);

        if(existingMeasurementStation != null){


            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");
            LocalDateTime localDateTime = LocalDateTime.parse(data.get("time"), formatter);
            Instant instant = localDateTime.toInstant(ZoneOffset.UTC);

            UV newUV = new UV();
            newUV.setTime(instant);
            newUV.setTemperature(Double.parseDouble(data.get("uv")));

            existingMeasurementStation.addUV(newUV);

            return uvService.saveUV(newUV);
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

        Map<Integer, Double> falls = this.uvService.getByDay(startTime, city).orElse(null);

        System.out.println(falls);

        return falls;
    }

    @GetMapping("/last")
    public UV getLast(@RequestParam String cityId){

        Integer city = Integer.parseInt(cityId);
        UV data = this.uvService.getLastFromCity(city).orElse(null);

        return data;
    }

}
