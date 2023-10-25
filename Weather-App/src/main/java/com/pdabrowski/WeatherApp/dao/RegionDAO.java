package com.pdabrowski.WeatherApp.dao;

import com.pdabrowski.WeatherApp.entity.Region;

import java.util.List;
import java.util.Optional;

public interface RegionDAO {

    public void save(Region theRegion);

    List<Region> findAll();

    public Optional<Region> findById(int theId);

}
