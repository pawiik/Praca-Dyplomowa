package com.pdabrowski.WeatherApp.service;
import com.pdabrowski.WeatherApp.entity.Temperature;

import java.util.List;
import java.util.Optional;
public interface TemperatureService {
    void saveTemperature(Temperature temperature);
    Optional<Temperature> getTemperatureById(int id);
    List<Temperature> getAllTemperatures();
    void deleteTemperature(Temperature temperature);
}
