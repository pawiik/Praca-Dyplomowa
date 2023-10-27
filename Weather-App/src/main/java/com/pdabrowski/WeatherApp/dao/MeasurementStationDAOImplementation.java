package com.pdabrowski.WeatherApp.dao;

import com.pdabrowski.WeatherApp.entity.MeasurementStation;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MeasurementStationDAOImplementation implements MeasurementStationDAO{

    EntityManager entityManager;

    @Autowired
    public MeasurementStationDAOImplementation(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Transactional
    @Override
    public void save(MeasurementStation theStation) {
        System.out.println("dupa save");
        this.entityManager.merge(theStation);

    }

    @Override
    public Optional<MeasurementStation> findById(int theId) {
        return Optional.empty();
    }

    @Override
    public List<MeasurementStation> findAll() {
        return null;
    }
}
