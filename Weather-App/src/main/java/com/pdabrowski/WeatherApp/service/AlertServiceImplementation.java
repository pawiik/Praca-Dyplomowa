package com.pdabrowski.WeatherApp.service;

import com.pdabrowski.WeatherApp.dao.AlertDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlertServiceImplementation implements AlertService{

    AlertDAO alertDAO;

    @Autowired
    public AlertServiceImplementation(AlertDAO alertDAO){
        this.alertDAO = alertDAO;
    }
}
