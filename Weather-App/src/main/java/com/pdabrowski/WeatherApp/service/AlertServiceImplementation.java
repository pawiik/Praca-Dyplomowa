package com.pdabrowski.WeatherApp.service;

import com.pdabrowski.WeatherApp.dao.AlertDAO;
import com.pdabrowski.WeatherApp.dao.UserDAO;
import com.pdabrowski.WeatherApp.entity.Alert;
import com.pdabrowski.WeatherApp.entity.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AlertServiceImplementation implements AlertService{

    private AlertDAO alertDao;
    private UserDAO userDAO;

    @Autowired
    public AlertServiceImplementation(AlertDAO alertDAO, UserDAO userDAO){
        this.alertDao = alertDAO;
        this.userDAO = userDAO;
    }

    @Override
    @Transactional
    public Alert saveAlert(Alert alert) {
        return this.alertDao.save(alert);
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

    @Override
    public List<Alert> getAlertsForCity(Integer cityId) {
        List<Alert> alerts = this.alertDao.getAlertsForCity(cityId).orElse(null);

        return alerts;
    }
    public List<Alert> getAlertsForUser(String userId) {
        User user = this.userDAO.findById(userId).orElse(null);
        if (user != null) {
            System.out.println(user.getRegions());
            return alertDao.findAlertsByRegions(new ArrayList<>(user.getRegions()));
        }
        return null;
    }

}
