package com.pdabrowski.WeatherApp.service;

import com.pdabrowski.WeatherApp.dao.RegionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegionServiceImplementation implements RegionService{

    RegionDAO regionDAO;

    @Autowired
    public RegionServiceImplementation(RegionDAO regionDAO){
        this.regionDAO = regionDAO;
    }

}
