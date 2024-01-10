package com.pdabrowski.WeatherApp.rest;

import com.pdabrowski.WeatherApp.entity.Alert;
import com.pdabrowski.WeatherApp.entity.Region;
import com.pdabrowski.WeatherApp.service.AlertService;
import com.pdabrowski.WeatherApp.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/alert")
public class AlertRestController {

    AlertService alertService;
    RegionService regionService;

    @Autowired
    public AlertRestController(AlertService alertService, RegionService regionService){
        this.alertService = alertService;
        this.regionService = regionService;
    }

    @GetMapping("/")
    public List<Alert> getAllAlerts(){
        return alertService.getAllAlerts();
    }

    @PostMapping("/")
    public Alert addNewAlert(@RequestBody Map<String, String> data){
        System.out.println(data.toString());

        Region region = regionService.getRegionById(Integer.valueOf(data.get("regionId")));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime start = LocalDateTime.parse(data.get("startTime"), formatter);
        LocalDateTime end = LocalDateTime.parse(data.get("endTime"), formatter);
        Instant startTime = start.toInstant(ZoneOffset.UTC);
        Instant endTime = end.toInstant(ZoneOffset.UTC);

        if(region != null){
            Alert newAlert = new Alert();
            newAlert.setAlertType(Integer.parseInt(data.get("alertType")));
            newAlert.setStartTime(startTime);
            newAlert.setEndTime(endTime);
            newAlert.setMessage(data.get("message"));

            region.addAlert(newAlert);

            Alert dbAlert = alertService.saveAlert(newAlert);
            System.out.println("gotId");
            return dbAlert;
        }
        else{
            return null;
        }


    }

    @GetMapping("/{cityName}")
    public ResponseEntity<List<Alert>> getAlertsForCity(@PathVariable String cityName) {
        Integer id = Integer.parseInt(cityName);
        try {
            List<Alert> alerts = alertService.getAlertsForCity(id);
            if (alerts.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(alerts);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
