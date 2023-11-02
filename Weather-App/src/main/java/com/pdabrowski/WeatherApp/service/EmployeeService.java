package com.pdabrowski.WeatherApp.service;

import com.pdabrowski.WeatherApp.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    void saveEmployee(Employee employee);
    Optional<Employee> getEmployeeById(int id);
    List<Employee> getAllEmployees();
    void deleteEmployee(Employee employee);
}
