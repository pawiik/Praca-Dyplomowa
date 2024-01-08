package com.pdabrowski.WeatherApp.rest;

import com.pdabrowski.WeatherApp.entity.Region;
import com.pdabrowski.WeatherApp.service.RegionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/region")
public class RegionRestController {

    RegionService regionService;

    private static final Logger logger = LoggerFactory.getLogger(RegionRestController.class);

    @Autowired
    public RegionRestController(RegionService regionService){
        this.regionService = regionService;
    }


    @GetMapping("/regions")
    public List<Region> findAll(){

        logger.info("RegionRestController: Finding all regions");

        List<Region> regions = regionService.getAllRegions();

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

    @PostMapping("/")
    public Region addRegion(@RequestBody Map<String, String> data){
        Region newRegion = new Region();
        newRegion.setName(data.get("regionName"));

        return regionService.saveRegion(newRegion);
    }

}
