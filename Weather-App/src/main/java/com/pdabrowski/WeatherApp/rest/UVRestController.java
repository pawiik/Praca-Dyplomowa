package com.pdabrowski.WeatherApp.rest;

import com.pdabrowski.WeatherApp.service.UVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/uv")
public class UVRestController {

    UVService uvService;

    @Autowired
    public UVRestController(UVService uvService){
        this.uvService = uvService;
    }

}
