package com.pdabrowski.WeatherApp.service;

import com.pdabrowski.WeatherApp.entity.Fall;

import java.util.List;
import java.util.Optional;

public interface FallService {
    Fall saveFall(Fall fall);
    Optional<Fall> getFallById(int id);
    List<Fall> getAllFalls();
    void deleteFall(Fall fall);
}
