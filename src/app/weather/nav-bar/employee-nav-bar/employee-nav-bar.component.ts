import { Component } from '@angular/core';
import {Router, RouterOutlet} from "@angular/router";
import {AuthService} from "../../services/auth-service";
import {EmployeeSharedService} from "../../employee/employee-shared-service";
import {MeasurementsTableComponent} from "../../measurements-table/measurements-table.component";
import {AlertsComponent} from "../../alerts/alerts.component";
import {DetailsComponent} from "../../welcome/details/details.component";
import {CitiesComponent} from "../../employee/cities/cities.component";

@Component({
  selector: 'app-employee-nav-bar',
  templateUrl: './employee-nav-bar.component.html',
  styleUrls: ['./employee-nav-bar.component.css']
})
export class EmployeeNavBarComponent {

  constructor(private router: Router,
              private auth: AuthService,
              private sharedService: EmployeeSharedService) {
  }

  logout(): void{
    this.auth.logoutUser()
    this.router.navigate(['/employee'])
  }

  isEmployee(): boolean{
    return this.auth.authData.userRole === "ROLE_EMPLOYEE"
  }

  isAuthenticated(): boolean{
    return this.auth.isUserAuthenticated();
  }

  goToMain(){
    this.sharedService.loadComponent(DetailsComponent)
    console.log("details")
  }

  goToAnalyseData(){
    this.sharedService.loadComponent(MeasurementsTableComponent)
    console.log("meas")
  }

  goToAlerts(){
    this.sharedService.loadComponent(AlertsComponent)
  }

  goToCities(){
    this.sharedService.loadComponent(CitiesComponent)
  }
}
