package com.pdabrowski.WeatherApp.dao;

import com.pdabrowski.WeatherApp.entity.Temperature;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
@Repository
public class TemperatureDAOImplementation implements TemperatureDAO{

    EntityManager entityManager;

    @Autowired
    public TemperatureDAOImplementation(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Transactional
    @Override
    public void save(Temperature theTemperature) {

        entityManager.merge(theTemperature);

    }
}
