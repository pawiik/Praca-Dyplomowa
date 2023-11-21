package com.pdabrowski.WeatherApp.dao;

import com.pdabrowski.WeatherApp.entity.Alert;
import com.pdabrowski.WeatherApp.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import com.pdabrowski.WeatherApp.entity.Region;

@Repository
public class UserDAOImplementation implements UserDAO{

    EntityManager entityManager;

    @Autowired
    public UserDAOImplementation(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
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
        String userId = theUser.getUserId();
        Query query = entityManager.createQuery("SELECT a FROM Alert a " +
                "INNER JOIN a.region ar " +
                "WHERE ar.id IN (" +
                "SELECT ur.regionId FROM User u " +
                "INNER JOIN u.regions ur " +
                "WHERE u.id = :userId)");
        query.setParameter("userId", userId);

        return query.getResultList();
    }
}
