import { Component } from '@angular/core';
import {AuthService} from "../../services/auth-service";
import {UserApiService} from "../../services/user-api-service";
import {User} from "../../../shared/model/User";
import {DataService} from "../city-details.component";
import {AlertApiService} from "../../services/alert-api-service";
import {Alert} from "../../../shared/model/Alert";
import {CityApiService} from "../../services/city-api-service";
import {City} from "../../../shared/model/City";

@Component({
  selector: 'app-city-alerts',
  templateUrl: './city-alerts.component.html',
  styleUrls: ['./city-alerts.component.css']
})
export class CityAlertsComponent {

  alerts: Alert[] = []
  doesSub: boolean = false
  city: any

  constructor(private authService: AuthService,
              private userService: UserApiService,
              private dataService: DataService,
              private alertService: AlertApiService,
              private cityService: CityApiService) {
    this.loadData()
    this.doesNotSubscribe()
    this.loadCity()

  }

  ngOnInit(){
    this.loadData()
  }


  isUser() {
    return this.authService.getUserData().userRole === "ROLE_USER"
  }

  doesSubscribe(){
    this.doesNotSubscribe()
    return this.doesSub
  }

  doesNotSubscribe() {
    let user: boolean = true
    this.userService.getUserInformation(this.authService.authData.username).subscribe(response => {
      response.regions.forEach(region => {
        if (region.regionId === this.dataService.getData().cityId) {
          user = true
        }
      })
    })
    this.doesSub = user
  }

  sub() {
    console.log("subbes")
    this.userService.addAlertToUser(this.dataService.getData().region.regionId.toString(), this.authService.authData.username).subscribe(response =>
    console.log(response))
    this.doesNotSubscribe()
  }

  unsub() {

  }

  private loadData() {
    this.alertService.getAlertForCity(this.dataService.getData().cityId.toString()).subscribe(response => {
      this.alerts = response
      console.log(response)
    })
  }

  private loadCity() {
    this.cityService.getCityById(this.dataService.getData().cityId).subscribe(response => this.city = response)
  }
}
