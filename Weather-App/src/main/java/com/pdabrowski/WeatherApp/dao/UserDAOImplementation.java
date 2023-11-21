package com.pdabrowski.WeatherApp.dao;

import com.pdabrowski.WeatherApp.entity.Alert;
import com.pdabrowski.WeatherApp.entity.User;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
@Repository
public class UserDAOImplementation implements UserDAO{

    EntityManager entityManager;

    @Autowired
    public UserDAOImplementation(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public User save(User user) {
        return entityManager.merge(user);
    }

    @Override
    public Optional<User> findById(String id) {
        return Optional.ofNullable(entityManager.find(User.class, id));
    }

    @Override
    public List<User> findAll() {
        return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    @Override
    public void delete(User user) {
        if (entityManager.contains(user)) {
            entityManager.remove(user);
        } else {
            entityManager.remove(entityManager.merge(user));
        }
    }

    @Override
    public List<Alert> getUserAlerts(User theUser) {

        return null;
    }
}
