package com.pdabrowski.WeatherApp.service;

import com.pdabrowski.WeatherApp.dao.WindDAO;
import com.pdabrowski.WeatherApp.entity.Wind;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class WindServiceImplementation implements WindService{

    WindDAO windDao;

    @Autowired
    public WindServiceImplementation(WindDAO windDAO){
        this.windDao = windDAO;
    }
    @Override
    @Transactional
    public void saveWind(Wind wind) {
        windDao.save(wind);
    }

    @Override
    @Transactional
    public Optional<Wind> getWindById(int id) {
        return windDao.findById(id);
    }

    @Override
    @Transactional
    public List<Wind> getAllWinds() {
        return windDao.findAll();
    }

    @Override
    @Transactional
    public void deleteWind(Wind wind) {
        windDao.delete(wind);
    }
}
