package com.pdabrowski.WeatherApp.dao;

import com.pdabrowski.WeatherApp.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeDAO {

    void save(Employee employee);
    Optional<Employee> findById(int id);
    List<Employee> findAll();
    void delete(Employee employee);



}
