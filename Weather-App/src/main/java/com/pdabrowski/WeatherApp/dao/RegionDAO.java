package com.pdabrowski.WeatherApp.dao;

import com.pdabrowski.WeatherApp.entity.Region;

import java.util.List;

public interface RegionDAO {

    public void save(Region theRegion);

    List<Region> findAll();

}
