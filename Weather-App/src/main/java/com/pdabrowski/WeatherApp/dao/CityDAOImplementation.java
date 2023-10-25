package com.pdabrowski.WeatherApp.dao;

import com.pdabrowski.WeatherApp.entity.City;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CityDAOImplementation implements CityDAO{

    private EntityManager entityManager;

    @Autowired
    public CityDAOImplementation(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public City save(City theCity) {
        entityManager.merge(theCity);

        return theCity;
    }

    @Override
    public List<City> findAll() {
        TypedQuery<City> query = entityManager.createQuery("FROM City ", City.class);

        List<City> cities = query.getResultList();

        return cities;
    }
}
