package com.pdabrowski.WeatherApp.dao;

import com.pdabrowski.WeatherApp.entity.MeasurementStation;

import java.util.List;
import java.util.Optional;

public interface MeasurementStationDAO {

    public void save(MeasurementStation theStation);

    public Optional<MeasurementStation> findById(int theId);

    public List<MeasurementStation> findAll();
}
