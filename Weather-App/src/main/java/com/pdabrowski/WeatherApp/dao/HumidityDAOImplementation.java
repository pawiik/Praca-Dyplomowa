package com.pdabrowski.WeatherApp.dao;

import com.pdabrowski.WeatherApp.entity.Humidity;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HumidityDAOImplementation implements HumidityDAO{

    EntityManager entityManager;

    @Autowired
    public HumidityDAOImplementation(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Transactional
    @Override
    public void save(Humidity theHumidity) {

        entityManager.merge(theHumidity);

    }
}
