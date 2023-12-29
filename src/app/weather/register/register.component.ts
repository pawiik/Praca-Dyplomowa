import { Component } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Router} from "@angular/router";
import {AuthService} from "../services/auth-service";
import {City} from "../../shared/model/City";
import {CityApiService} from "../services/city-api-service";



@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {

  formData = {cityId:'', name: '', lastName: '', emailAddress: '', password: '', confirmPassword: '' };
  cities: City [] = []


  constructor(private http: HttpClient, private router: Router, private authService: AuthService, private cityService: CityApiService) {
    this.loadCities()
  }

  loadCities(){
    this.cityService.getAllCities().subscribe(response => this.cities = response)
  }

  register() {

    if (this.formData.password !== this.formData.confirmPassword) {
      alert("Passwords do not match.");
      return;
    }
    let body = this.formData

    this.authService.registerUser(body)
    this.router.navigate(['/login'])
  }
}



