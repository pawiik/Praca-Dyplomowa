package com.pdabrowski.WeatherApp.dao;

import com.pdabrowski.WeatherApp.entity.MeasurementStation;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.List;
import java.util.Optional;
@Repository
public class MeasurementStationDAOImplementation implements MeasurementStationDAO{

    EntityManager entityManager;

    @Autowired
    public MeasurementStationDAOImplementation(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public MeasurementStation save(MeasurementStation station) {
        return entityManager.merge(station);
    }

    @Override
    public Optional<MeasurementStation> findById(int id) {
        return Optional.ofNullable(entityManager.find(MeasurementStation.class, id));
    }

    @Override
    public List<MeasurementStation> findAll() {
        return entityManager.createQuery("SELECT m FROM MeasurementStation m", MeasurementStation.class).getResultList();
    }

    @Override
    public void delete(MeasurementStation station) {
        if (entityManager.contains(station)) {
            entityManager.remove(station);
        } else {
            entityManager.remove(entityManager.merge(station));
        }
    }
}
