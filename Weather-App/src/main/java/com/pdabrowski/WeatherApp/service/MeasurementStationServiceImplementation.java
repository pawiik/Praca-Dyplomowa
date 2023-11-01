package com.pdabrowski.WeatherApp.service;

import com.pdabrowski.WeatherApp.dao.MeasurementStationDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MeasurementStationServiceImplementation implements MeasurementStationService {

    MeasurementStationDAO measurementStationDAO;

    @Autowired
    public MeasurementStationServiceImplementation(MeasurementStationDAO measurementStationDAO){
        this.measurementStationDAO = measurementStationDAO;
    }

}
