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

    @Override
    public void save(Temperature temperature) {
        entityManager.persist(temperature);
    }

    @Override
    public Optional<Temperature> findById(int id) {
        return Optional.ofNullable(entityManager.find(Temperature.class, id));
    }

    @Override
    public List<Temperature> findAll() {
        return entityManager.createQuery("SELECT t FROM Temperature t", Temperature.class).getResultList();
    }

    @Override
    public void delete(Temperature temperature) {
        if (entityManager.contains(temperature)) {
            entityManager.remove(temperature);
        } else {
            entityManager.remove(entityManager.merge(temperature));
        }
    }
}
