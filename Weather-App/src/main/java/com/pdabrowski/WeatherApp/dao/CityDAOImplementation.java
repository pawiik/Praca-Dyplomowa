package com.pdabrowski.WeatherApp.dao;

import com.pdabrowski.WeatherApp.entity.City;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CityDAOImplementation implements CityDAO{

    private EntityManager entityManager;

    @Autowired
    public CityDAOImplementation(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public void save(City city) {
        entityManager.merge(city);
    }

    @Override
    public Optional<City> findById(int id) {
        return Optional.ofNullable(entityManager.find(City.class, id));
    }

    @Override
    public List<City> findAll() {
        return entityManager.createQuery("SELECT c FROM City c", City.class).getResultList();
    }

    @Override
    public void delete(City city) {
        if (entityManager.contains(city)) {
            entityManager.remove(city);
        } else {
            entityManager.remove(entityManager.merge(city));
        }
    }
}
