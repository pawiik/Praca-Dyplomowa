package com.pdabrowski.WeatherApp.dao;

import com.pdabrowski.WeatherApp.entity.Alert;
import com.pdabrowski.WeatherApp.entity.Region;
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
    @Transactional
    public Alert save(Alert alert) {
       return entityManager.merge(alert);
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
    @Transactional
    public void delete(Alert alert) {
        if (entityManager.contains(alert)) {
            entityManager.remove(alert);
        } else {
            entityManager.remove(entityManager.merge(alert));
        }
    }

    @Transactional
    public Optional<List<Alert>> getAlertsForCity(Integer cityId) {
        List<Alert> alerts = null;
        try {
            TypedQuery<Integer> regionQuery = entityManager.createQuery(
                    "SELECT c.region.id FROM City c WHERE c.cityId = :cityId", Integer.class);
            regionQuery.setParameter("cityId", cityId);
            Integer regionId = regionQuery.getSingleResult();

            TypedQuery<Alert> alertQuery = entityManager.createQuery(
                    "SELECT a FROM Alert a WHERE a.region.id = :regionId", Alert.class);
            alertQuery.setParameter("regionId", regionId);

            alerts = alertQuery.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(alerts);
    }


    public List<Alert> findAlertsByRegions(List<Region> regions) {
        String qlString = "SELECT a FROM Alert a WHERE a.region IN :regions";
        TypedQuery<Alert> query = entityManager.createQuery(qlString, Alert.class);
        query.setParameter("regions", regions);
        return query.getResultList();
    }
}
