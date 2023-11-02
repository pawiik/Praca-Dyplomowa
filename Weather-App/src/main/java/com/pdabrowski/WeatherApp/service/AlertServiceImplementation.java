package com.pdabrowski.WeatherApp.service;

import com.pdabrowski.WeatherApp.dao.AlertDAO;
import com.pdabrowski.WeatherApp.entity.Alert;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlertServiceImplementation implements AlertService{

    private AlertDAO alertDao;

    @Autowired
    public AlertServiceImplementation(AlertDAO alertDAO){
        this.alertDao = alertDAO;
    }

    @Override
    @Transactional
    public void saveAlert(Alert alert) {
        this.alertDao.save(alert);
    }

    @Override
    @Transactional
    public Optional<Alert> getAlertById(int id) {
        return alertDao.findById(id);
    }

    @Override
    @Transactional
    public List<Alert> getAllAlerts() {
        return alertDao.findAll();
    }

    @Override
    @Transactional
    public void deleteAlert(Alert alert) {
        alertDao.delete(alert);
    }
}
