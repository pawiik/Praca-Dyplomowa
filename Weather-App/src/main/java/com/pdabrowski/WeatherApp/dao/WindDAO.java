package com.pdabrowski.WeatherApp.dao;

import com.pdabrowski.WeatherApp.entity.Wind;

import java.util.List;
import java.util.Optional;

public interface WindDAO {

    public void save(Wind theWind);

    public Optional<Wind> findById(int theId);

    public Optional<List<Wind>> findAll();

}
