package com.pdabrowski.WeatherApp.rest;

import com.pdabrowski.WeatherApp.entity.Alert;
import com.pdabrowski.WeatherApp.entity.Region;
import com.pdabrowski.WeatherApp.service.AlertService;
import com.pdabrowski.WeatherApp.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

        Alert newAlert = new Alert();
        newAlert.setAlertType(Integer.parseInt(data.get("alertType")));
        newAlert.setStartTime(Integer.parseInt(data.get("startTime")));
        newAlert.setEndTime(Integer.parseInt(data.get("endTime")));
        newAlert.setMessage(data.get("message"));

        region.addAlert(newAlert);

        Alert dbAlert = alertService.saveAlert(newAlert);
        System.out.println("gotId");
        return dbAlert;
    }


}
