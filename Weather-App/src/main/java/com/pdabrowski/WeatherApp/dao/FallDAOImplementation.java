package com.pdabrowski.WeatherApp.dao;

import com.pdabrowski.WeatherApp.entity.Employee;
import com.pdabrowski.WeatherApp.entity.Fall;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class FallDAOImplementation implements FallDAO{

    EntityManager entityManager;


    @Autowired
    public FallDAOImplementation(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public void save(Fall fall) {
        entityManager.persist(fall);
    }

    @Override
    public Optional<Fall> findById(int id) {
        return Optional.ofNullable(entityManager.find(Fall.class, id));
    }

    @Override
    public List<Fall> findAll() {
        return entityManager.createQuery("SELECT f FROM Fall f", Fall.class).getResultList();
    }

    @Override
    public void delete(Fall fall) {
        if (entityManager.contains(fall)) {
            entityManager.remove(fall);
        } else {
            entityManager.remove(entityManager.merge(fall));
        }
    }
}
