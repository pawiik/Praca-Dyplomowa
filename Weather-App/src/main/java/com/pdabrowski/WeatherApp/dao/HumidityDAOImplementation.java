package com.pdabrowski.WeatherApp.dao;

import com.pdabrowski.WeatherApp.entity.Humidity;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
@Repository
public class HumidityDAOImplementation implements HumidityDAO{

    EntityManager entityManager;

    @Autowired
    public HumidityDAOImplementation(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public void save(Humidity humidity) {
        entityManager.merge(humidity);
    }

    @Override
    public Optional<Humidity> findById(int id) {
        return Optional.ofNullable(entityManager.find(Humidity.class, id));
    }

    @Override
    public List<Humidity> findAll() {
        return entityManager.createQuery("SELECT h FROM Humidity h", Humidity.class).getResultList();
    }

    @Override
    public void delete(Humidity humidity) {
        if (entityManager.contains(humidity)) {
            entityManager.remove(humidity);
        } else {
            entityManager.remove(entityManager.merge(humidity));
        }
    }
}
