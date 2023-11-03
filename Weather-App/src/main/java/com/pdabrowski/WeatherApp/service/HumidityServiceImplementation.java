package com.pdabrowski.WeatherApp.service;

import com.pdabrowski.WeatherApp.dao.HumidityDAO;
import com.pdabrowski.WeatherApp.entity.Humidity;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class HumidityServiceImplementation implements HumidityService{

    HumidityDAO humidityDao;

    @Autowired
    public HumidityServiceImplementation(HumidityDAO humidityDAO){
        this.humidityDao = humidityDAO;
    }

    @Override
    @Transactional
    public Humidity saveHumidity(Humidity humidity) {
        return humidityDao.save(humidity);
    }

    @Override
    @Transactional
    public Optional<Humidity> getHumidityById(int id) {
        return humidityDao.findById(id);
    }

    @Override
    @Transactional
    public List<Humidity> getAllHumidities() {
        return humidityDao.findAll();
    }

    @Override
    @Transactional
    public void deleteHumidity(Humidity humidity) {
        humidityDao.delete(humidity);
    }
}
