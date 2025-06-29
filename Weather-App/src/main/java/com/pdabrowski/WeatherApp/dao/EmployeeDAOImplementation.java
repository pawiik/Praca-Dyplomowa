package com.pdabrowski.WeatherApp.dao;

import com.pdabrowski.WeatherApp.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EmployeeDAOImplementation implements EmployeeDAO{

    EntityManager entityManager;

    @Autowired
    public EmployeeDAOImplementation(EntityManager entityManager){
        this.entityManager = entityManager;
    }


    @Override
    @Transactional
    public Employee save(Employee employee) {
        return entityManager.merge(employee);
    }

    @Override
    public Optional<Employee> findById(int id) {
        return Optional.ofNullable(entityManager.find(Employee.class, id));
    }

    @Override
    public List<Employee> findAll() {
        return entityManager.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();
    }

    @Override
    public void delete(Employee employee) {
        if (entityManager.contains(employee)) {
            entityManager.remove(employee);
        } else {
            entityManager.remove(entityManager.merge(employee));
        }
    }
}
