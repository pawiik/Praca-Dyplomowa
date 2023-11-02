package com.pdabrowski.WeatherApp.dao;

import com.pdabrowski.WeatherApp.entity.Temperature;
import java.util.List;
import java.util.Optional;
public interface TemperatureDAO {

    public void save(Temperature theTemperature);

}
