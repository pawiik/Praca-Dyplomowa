package com.pdabrowski.WeatherApp.dao;

import com.pdabrowski.WeatherApp.entity.Fall;

import java.util.List;
import java.util.Optional;

public interface FallDAO {

    void save(Fall fall);
    Optional<Fall> findById(int id);
    List<Fall> findAll();
    void delete(Fall fall);

}
