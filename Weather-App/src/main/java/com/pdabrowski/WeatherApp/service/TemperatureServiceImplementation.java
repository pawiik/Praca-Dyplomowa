package com.pdabrowski.WeatherApp.service;

import com.pdabrowski.WeatherApp.dao.TemperatureDAO;
import com.pdabrowski.WeatherApp.entity.Temperature;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class TemperatureServiceImplementation implements TemperatureService {

    TemperatureDAO temperatureDao;

    @Autowired
    public TemperatureServiceImplementation(TemperatureDAO temperatureDAO){
        this.temperatureDao = temperatureDAO;
    }
    @Override
    @Transactional
    public void saveTemperature(Temperature temperature) {
        temperatureDao.save(temperature);
    }

    @Override
    @Transactional
    public Optional<Temperature> getTemperatureById(int id) {
        return temperatureDao.findById(id);
    }

    @Override
    @Transactional
    public List<Temperature> getAllTemperatures() {
        return temperatureDao.findAll();
    }

    @Override
    @Transactional
    public void deleteTemperature(Temperature temperature) {
        temperatureDao.delete(temperature);
    }
}
