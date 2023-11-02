package com.pdabrowski.WeatherApp.service;

import com.pdabrowski.WeatherApp.dao.EmployeeDAO;
import com.pdabrowski.WeatherApp.entity.Employee;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImplementation implements EmployeeService{

    EmployeeDAO employeeDao;

    @Autowired
    public EmployeeServiceImplementation(EmployeeDAO employeeDAO){
        this.employeeDao = employeeDAO;
    }

    @Override
    @Transactional
    public void saveEmployee(Employee employee) {
        employeeDao.save(employee);
    }

    @Override
    @Transactional
    public Optional<Employee> getEmployeeById(int id) {
        return employeeDao.findById(id);
    }

    @Override
    @Transactional
    public List<Employee> getAllEmployees() {
        return employeeDao.findAll();
    }

    @Override
    @Transactional
    public void deleteEmployee(Employee employee) {
        employeeDao.delete(employee);
    }
}
