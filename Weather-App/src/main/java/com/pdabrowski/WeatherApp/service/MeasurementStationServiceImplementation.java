package com.pdabrowski.WeatherApp.service;

import com.pdabrowski.WeatherApp.dao.MeasurementStationDAO;
import com.pdabrowski.WeatherApp.entity.MeasurementStation;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class MeasurementStationServiceImplementation implements MeasurementStationService {

    MeasurementStationDAO stationDao;

    @Autowired
    public MeasurementStationServiceImplementation(MeasurementStationDAO measurementStationDAO){
        this.stationDao = measurementStationDAO;
    }
    @Override
    @Transactional
    public void saveStation(MeasurementStation station) {
        stationDao.save(station);
    }

    @Override
    @Transactional
    public Optional<MeasurementStation> getStationById(int id) {
        return stationDao.findById(id);
    }

    @Override
    @Transactional
    public List<MeasurementStation> getAllStations() {
        return stationDao.findAll();
    }

    @Override
    @Transactional
    public void deleteStation(MeasurementStation station) {
        stationDao.delete(station);
    }
}
