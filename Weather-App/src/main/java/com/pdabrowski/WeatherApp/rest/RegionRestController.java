package com.pdabrowski.WeatherApp.rest;

import com.pdabrowski.WeatherApp.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/region")
public class RegionRestController {

    RegionService regionService;

    @Autowired
    public RegionRestController(RegionService regionService){
        this.regionService = regionService;
    }

}
