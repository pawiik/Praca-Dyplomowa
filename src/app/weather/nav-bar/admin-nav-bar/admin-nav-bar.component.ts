import { Component } from '@angular/core';
import {DetailsComponent} from "../../welcome/details/details.component";
import {EmployeeSharedService} from "../../employee/employee-shared-service";
import {AuthService} from "../../services/auth-service";
import {Router} from "@angular/router";
import {EmployeeComponent} from "../../employee/employee.component";
import {EmployeesComponent} from "../../admin/employees/employees.component";

@Component({
  selector: 'app-admin-nav-bar',
  templateUrl: './admin-nav-bar.component.html',
  styleUrls: ['./admin-nav-bar.component.css']
})
export class AdminNavBarComponent {

  constructor(private sharedService: EmployeeSharedService,
              private auth: AuthService,
              private router: Router) {
    this.sharedService.loadComponent(EmployeesComponent)
  }

  goToMain(){
    this.sharedService.loadComponent(DetailsComponent)
    console.log("details")
  }

  logout(): void{
    this.auth.logoutUser()
    this.router.navigate(['/login'])
  }

  employees(){
    this.sharedService.loadComponent(EmployeesComponent)
  }

}
