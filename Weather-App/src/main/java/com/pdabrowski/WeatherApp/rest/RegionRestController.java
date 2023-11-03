package com.pdabrowski.WeatherApp.rest;

import com.pdabrowski.WeatherApp.entity.Region;
import com.pdabrowski.WeatherApp.service.RegionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RegionRestController {

    RegionService regionService;

    private static final Logger logger = LoggerFactory.getLogger(RegionRestController.class);

    @Autowired
    public RegionRestController(RegionService regionService){
        this.regionService = regionService;
    }


    @GetMapping("/regions")
    public List<Region> findAll(){

        // Log the entry into the method
        logger.info("RegionRestController: Finding all regions");

        // Do the actual work
        List<Region> regions = regionService.getAllRegions();

        // Log the result
        logger.info("RegionRestController: Found {} regions", regions.size());

        return regions;
    }

    @GetMapping("/regions/{id}")
    public Region getById(@PathVariable int id){
        Region theEmployee;
        try{
            theEmployee = regionService.getRegionById(id);
        }catch (Exception e){
            throw new RuntimeException("Employee id not found");
        }

        if(theEmployee == null){
            throw new RuntimeException("Employee id not found");
        }

        return theEmployee;
    }

}
