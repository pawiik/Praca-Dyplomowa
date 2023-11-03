package com.pdabrowski.WeatherApp.rest;

import com.pdabrowski.WeatherApp.service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/alert")
public class AlertRestController {

    AlertService alertService;

    @Autowired
    public AlertRestController(AlertService alertService){
        this.alertService = alertService;
    }




}
