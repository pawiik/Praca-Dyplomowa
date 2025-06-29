import { Component } from '@angular/core';
import {Router} from "@angular/router";
import {AuthService} from "../../services/auth-service";
import {EmployeeSharedService} from "../../employee/employee-shared-service";
import {DetailsComponent} from "../../welcome/details/details.component";
import {MeasurementsTableComponent} from "../../measurements-table/measurements-table.component";
import {AlertsComponent} from "../../alerts/alerts.component";
import {CitiesComponent} from "../../employee/cities/cities.component";
import {MeasurementStationsComponent} from "../../employee/measurement-stations/measurement-stations.component";
import {UserInformationComponent} from "../../user-information/user-information.component";

@Component({
  selector: 'app-user-nav-bar',
  templateUrl: './user-nav-bar.component.html',
  styleUrls: ['./user-nav-bar.component.css']
})
export class UserNavBarComponent {

  constructor(private router: Router,
              private auth: AuthService,
              private sharedService: EmployeeSharedService) {
  }

  logout(): void{
    this.auth.logoutUser()
    this.router.navigate(['/login'])
  }

  isAuthenticated(): boolean{
    return this.auth.isUserAuthenticated();
  }

  goToMain(){
    this.sharedService.loadComponent(DetailsComponent)
  }

  goToAccount(){
    this.sharedService.loadComponent(UserInformationComponent)
  }


}
