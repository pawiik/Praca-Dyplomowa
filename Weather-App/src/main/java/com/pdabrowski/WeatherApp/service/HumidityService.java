package com.pdabrowski.WeatherApp.service;

import com.pdabrowski.WeatherApp.entity.Humidity;

import java.util.List;
import java.util.Optional;

public interface HumidityService {
    Humidity saveHumidity(Humidity humidity);
    Optional<Humidity> getHumidityById(int id);
    List<Humidity> getAllHumidities();
    void deleteHumidity(Humidity humidity);
}
