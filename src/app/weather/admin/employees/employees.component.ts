import { Component } from '@angular/core';
import {EmployeeApiService} from "../../services/employee-api-service";
import {EmployeeComponent} from "../../employee/employee.component";
import {Employee} from "../../../shared/model/Employee";

@Component({
  selector: 'app-employees',
  templateUrl: './employees.component.html',
  styleUrls: ['./employees.component.css']
})
export class EmployeesComponent {

  employees: Employee[] = []

  constructor(private employeeService: EmployeeApiService) {
    this.loadEmployees()
  }

  private loadEmployees() {
    this.employeeService.getAllEmployees().subscribe(response => this.employees = response)
  }
}
