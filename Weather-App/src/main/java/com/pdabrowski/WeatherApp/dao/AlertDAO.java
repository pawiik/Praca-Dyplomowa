package com.pdabrowski.WeatherApp.dao;

import com.pdabrowski.WeatherApp.entity.Alert;

import java.util.List;

public interface AlertDAO {

    public Alert save(Alert theAlert);

    public List<Alert> findAll();
}
