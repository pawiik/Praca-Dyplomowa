package com.pdabrowski.WeatherApp.dao;

import com.pdabrowski.WeatherApp.entity.Humidity;

import java.util.List;
import java.util.Optional;

public interface HumidityDAO {

    void save(Humidity humidity);
    Optional<Humidity> findById(int id);
    List<Humidity> findAll();
    void delete(Humidity humidity);}
