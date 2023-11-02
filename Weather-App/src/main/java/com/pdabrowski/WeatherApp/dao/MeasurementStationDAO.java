package com.pdabrowski.WeatherApp.dao;

import com.pdabrowski.WeatherApp.entity.MeasurementStation;

import java.util.List;
import java.util.Optional;
import java.util.List;
import java.util.Optional;
public interface MeasurementStationDAO {

    void save(MeasurementStation station);
    Optional<MeasurementStation> findById(int id);
    List<MeasurementStation> findAll();
    void delete(MeasurementStation station);
}
