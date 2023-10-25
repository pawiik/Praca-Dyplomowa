package com.pdabrowski.WeatherApp.dao;

import com.pdabrowski.WeatherApp.entity.Alert;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AlertDAOImplementation implements AlertDAO{

    private EntityManager entityManager;

    @Autowired
    public AlertDAOImplementation(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public Alert save(Alert theAlert) {
        Alert alert = entityManager.merge(theAlert);

        return alert;
    }

    @Override
    public List<Alert> findAll() {

        TypedQuery<Alert> query = entityManager.createQuery("FROM Alert ", Alert.class);

        List<Alert> alerts = query.getResultList();

        return alerts;

    }
}
