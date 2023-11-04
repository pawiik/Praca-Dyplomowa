package com.pdabrowski.WeatherApp.dao;

import com.pdabrowski.WeatherApp.entity.Wind;

import java.util.List;
import java.util.Optional;
import java.util.List;
import java.util.Optional;
public interface WindDAO {

    Wind save(Wind wind);
    Optional<Wind> findById(int id);
    List<Wind> findAll();
    void delete(Wind wind);

}
