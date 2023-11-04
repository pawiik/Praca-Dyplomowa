package com.pdabrowski.WeatherApp.service;
import com.pdabrowski.WeatherApp.entity.Wind;

import java.util.List;
import java.util.Optional;
public interface WindService {
    Wind saveWind(Wind wind);
    Optional<Wind> getWindById(int id);
    List<Wind> getAllWinds();
    void deleteWind(Wind wind);
}
