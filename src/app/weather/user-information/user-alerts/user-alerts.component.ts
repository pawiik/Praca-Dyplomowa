import { Component } from '@angular/core';
import {AlertApiService} from "../../services/alert-api-service";
import {Alert} from "../../../shared/model/Alert";
import {AuthService} from "../../services/auth-service";
import {withHashLocation} from "@angular/router";

@Component({
  selector: 'app-user-alerts',
  templateUrl: './user-alerts.component.html',
  styleUrls: ['./user-alerts.component.css']
})
export class UserAlertsComponent {

  alerts: Alert[] = []

  constructor(private alertService: AlertApiService,
              private authService: AuthService
              ) {
    this.loadAlerts()
  }



  loadAlerts(){
    console.log("lkoaa")
    // console.log(this.authService.isUserAuthenticated())
    // console.log(this.authService.getUserData())
    // console.log(this.authService.getUserData().username)
    // console.log(this.authService.authData.username)
    this.alertService.getAlertsForUser(this.authService.getUserData().username).subscribe(response => this.alerts = response)
  }

}
