package com.pdabrowski.WeatherApp.rest;

import com.pdabrowski.WeatherApp.entity.Employee;
import com.pdabrowski.WeatherApp.entity.MeasurementStation;
import com.pdabrowski.WeatherApp.service.EmployeeService;
import com.pdabrowski.WeatherApp.service.MeasurementStationService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employee")
public class EmployeeRestController {

    EmployeeService employeeService;
    MeasurementStationService measurementStationService;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService, MeasurementStationService measurementStationService){
        this.employeeService = employeeService;
        this.measurementStationService = measurementStationService;
    }

    @GetMapping("/employees")
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    @GetMapping("/")
    public Employee getEmployeeById(@RequestBody Map<String, Integer> data){
        return employeeService.getEmployeeById(data.get("employeeId")).orElse(null);
    }

    @PostMapping("/")
    public Employee addNewEmployee(@RequestBody Map<String, String> data){
        MeasurementStation existingMeasurementStation = measurementStationService.getStationById(Integer.parseInt(data.get("stationId"))).orElse(null);
        Employee newEmployee = new Employee();
        if(existingMeasurementStation != null)
        {
            newEmployee.setAddress(data.get("address"));
            newEmployee.setName(data.get("name"));
            newEmployee.setLastName(data.get("lastName"));
            newEmployee.setPhoneNumber(Integer.parseInt(data.get("phoneNumber")));

            existingMeasurementStation.addEmployee(newEmployee);

            Employee dbEmployee = employeeService.saveEmployee(newEmployee);

            return dbEmployee;
        }
        return null;
    }

}
