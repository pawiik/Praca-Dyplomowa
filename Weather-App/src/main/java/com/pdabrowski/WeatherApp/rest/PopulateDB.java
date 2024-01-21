package com.pdabrowski.WeatherApp.rest;

import com.pdabrowski.WeatherApp.entity.*;
import com.pdabrowski.WeatherApp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/populate")
public class PopulateDB {

    RegionService regionService;
    CityService cityService;
    MeasurementStationService measurementStationService;
    FallService fallService;
    AlertService alertService;

    @Autowired
    public PopulateDB(RegionService regionService,
                      CityService cityService,
                      MeasurementStationService measurementStationService,
                      FallService fallService,
                      AlertService alertService){
        this.regionService = regionService;
        this.cityService = cityService;
        this.measurementStationService = measurementStationService;
        this.fallService = fallService;
        this.alertService = alertService;
    }

    @GetMapping("/")
    public void populate(){
        List<String> regionNames = Arrays.asList("Northland", "East Coast", "South Valley", "West Ridge", "Central Plains",
                "Highland", "Lowland", "Lakeside", "Coastal Area", "Mountain Region");

        for (String name : regionNames) {
            Region newRegion = new Region();
            newRegion.setName(name);
            regionService.saveRegion(newRegion);
        }

        List<String> cityNames = Arrays.asList("Springfield", "Rivertown", "Oakwood", "Maple City", "Cedarville",
                "Pineview", "Lakeview", "Hilltop", "Sunnydale", "Stonebridge");

        for (int i = 1; i <= 10; i++) {
            Region existingRegion = regionService.getRegionById(i);

            City newCity = new City();
            newCity.setCityName(cityNames.get(i - 1));

            existingRegion.addCity(newCity);

            cityService.saveCity(newCity);
            regionService.saveRegion(existingRegion);
        }

        List<String> stationAddresses = Arrays.asList("123 Main St", "456 Elm St", "789 Oak St", "101 Pine Ave", "202 Maple Ave",
                "303 Birch Rd", "404 Cedar Ln", "505 Spruce Dr", "606 Ash Blvd", "707 Willow Ct");

        for (int cityId = 1; cityId <= 10; cityId++) {
            City existingCity = cityService.getCityById(cityId).orElse(null);

            if (existingCity != null) {
                MeasurementStation newStation = new MeasurementStation();
                newStation.setAddress(stationAddresses.get(cityId - 1));
                newStation.setRegionId(existingCity.getRegion().getRegionId());

                existingCity.addMeasurementStation(newStation);

                measurementStationService.saveStation(newStation);
            }
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime startDateTime = LocalDateTime.of(2024, Month.JANUARY, 1, 0, 0, 0);

        for (int i = 1; i <= 100; i++) {
            int stationId = (i % 10) + 1;

            MeasurementStation existingMeasurementStation = measurementStationService.getStationById(stationId).orElse(null);

            if (existingMeasurementStation != null) {
                LocalDateTime currentDateTime = startDateTime.plusHours(i - 1);
                Instant time = currentDateTime.toInstant(ZoneOffset.UTC);
                String timeStr = currentDateTime.format(formatter);

                double fallValue = 2;

                Fall newFall = new Fall();
                newFall.setTime(time);
                newFall.setTemperature(fallValue);

                existingMeasurementStation.addFall(newFall);

                fallService.saveFall(newFall);
            }
        }

        LocalDateTime baseStart = LocalDateTime.of(2024, Month.JANUARY, 1, 0, 0);
        LocalDateTime baseEnd = baseStart.plusHours(2);

        for (int i = 1; i <= 10; i++) {
            Region region = regionService.getRegionById(i);

            LocalDateTime start = baseStart.plusDays(i - 1);
            LocalDateTime end = baseEnd.plusDays(i - 1);
            Instant startTime = start.toInstant(ZoneOffset.UTC);
            Instant endTime = end.toInstant(ZoneOffset.UTC);

            if (region != null) {
                Alert newAlert = new Alert();
                newAlert.setAlertType(2);
                newAlert.setStartTime(startTime);
                newAlert.setEndTime(endTime);
                newAlert.setMessage("dangerous weathher");

                region.addAlert(newAlert);

                Alert dbAlert = alertService.saveAlert(newAlert);
                System.out.println("Alert ID: " + dbAlert.getAlertId());
            } else {
                System.out.println("Region not found for ID: " + i);
            }
        }

    }
}
