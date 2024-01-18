package com.pdabrowski.WeatherApp.dao;

import com.pdabrowski.WeatherApp.entity.Alert;
import com.pdabrowski.WeatherApp.entity.Region;

import java.util.List;
import java.util.Optional;

public interface AlertDAO {

    Alert save(Alert alert);

    Optional<Alert> findById(int id);

    List<Alert> findAll();

    void delete(Alert alert);

    public Optional<List<Alert>> getAlertsForCity(Integer cityId);

    public List<Alert> findAlertsByRegions(List<Region> regions);

}

