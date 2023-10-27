package com.pdabrowski.WeatherApp.dao;

import com.pdabrowski.WeatherApp.entity.City;

import java.util.List;
import java.util.Optional;

public interface CityDAO {
    public City save(City theCity);

    public List<City> findAll();

    public Optional<City> findById(int theId);
}
