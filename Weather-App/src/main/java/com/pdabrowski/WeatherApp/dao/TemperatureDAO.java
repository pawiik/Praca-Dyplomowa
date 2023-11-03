package com.pdabrowski.WeatherApp.dao;

import com.pdabrowski.WeatherApp.entity.Temperature;
import java.util.List;
import java.util.Optional;
public interface TemperatureDAO {

    Temperature save(Temperature temperature);
    Optional<Temperature> findById(int id);
    List<Temperature> findAll();
    void delete(Temperature temperature);

}
