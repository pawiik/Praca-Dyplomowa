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
    public void delete(Alert alert) {
        if (entityManager.contains(alert)) {
            entityManager.remove(alert);
        } else {
            entityManager.remove(entityManager.merge(alert));
        }
    }

    public Optional<List<Alert>> getAlertsForCity(Integer cityId) {
        EntityManager em = this.entityManager;
        List<Alert> alerts = null;
        try {
            em.getTransaction().begin();

            TypedQuery<Integer> regionQuery = em.createQuery(
                    "SELECT c.regionId FROM City c WHERE c.cityId = :cityId", Integer.class);
            regionQuery.setParameter("cityId", cityId);
            Integer regionId = regionQuery.getSingleResult();

            TypedQuery<Alert> alertQuery = em.createQuery(
                    "SELECT a FROM Alert a WHERE a.region.id = :regionId", Alert.class);
            alertQuery.setParameter("regionId", regionId);

            alerts = alertQuery.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(alerts);
    }
}
