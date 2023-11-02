package com.pdabrowski.WeatherApp.service;

import com.pdabrowski.WeatherApp.entity.MeasurementStation;

import java.util.List;
import java.util.Optional;

public interface MeasurementStationService {
    void saveStation(MeasurementStation station);
    Optional<MeasurementStation> getStationById(int id);
    List<MeasurementStation> getAllStations();
    void deleteStation(MeasurementStation station);
}
