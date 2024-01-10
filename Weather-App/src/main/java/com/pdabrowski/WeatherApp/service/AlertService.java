package com.pdabrowski.WeatherApp.service;

import com.pdabrowski.WeatherApp.entity.Alert;

import java.util.List;
import java.util.Optional;

public interface AlertService {
    Alert saveAlert(Alert alert);
    Optional<Alert> getAlertById(int id);
    List<Alert> getAllAlerts();
    void deleteAlert(Alert alert);

    List<Alert> getAlertsForCity(Integer cityId);
}
