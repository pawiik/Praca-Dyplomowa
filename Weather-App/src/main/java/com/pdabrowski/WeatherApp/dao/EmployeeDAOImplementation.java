package com.pdabrowski.WeatherApp.dao;

import com.pdabrowski.WeatherApp.entity.Employee;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDAOImplementation implements EmployeeDAO{

    EntityManager entityManager;

    @Autowired
    public EmployeeDAOImplementation(EntityManager entityManager){
        this.entityManager = entityManager;
    }


    @Override
    public void save(Employee theEmployee) {

    }
}
