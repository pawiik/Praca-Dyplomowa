package com.pdabrowski.WeatherApp.service;

import com.pdabrowski.WeatherApp.entity.Region;
import com.pdabrowski.WeatherApp.entity.User;

import java.util.List;

public interface RegionService {

    Region saveRegion(Region region);
    void addUserToRegion(User user, Region region);
    Region getRegionById(Integer id);
    List<Region> getAllRegions();

    public Region addRegion(Region region);
}
