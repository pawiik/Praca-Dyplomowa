package com.pdabrowski.WeatherApp.dao;

import com.pdabrowski.WeatherApp.entity.Alert;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AlertDAOImplementation implements AlertDAO{

    private EntityManager entityManager;

    @Autowired
    public AlertDAOImplementation(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public void save(Alert alert) {
        entityManager.merge(alert);
    }

    @Override
    public Optional<Alert> findById(int id) {
        return Optional.ofNullable(entityManager.find(Alert.class, id));
    }

    @Override
    public List<Alert> findAll() {
        return entityManager.createQuery("SELECT a FROM Alert a", Alert.class)
                .getResultList();
    }

    @Override
    public void delete(Alert alert) {
        if (entityManager.contains(alert)) {
            entityManager.remove(alert);
        } else {
            entityManager.remove(entityManager.merge(alert));
        }
    }
}
