import { Component } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Router} from "@angular/router";
import {AuthService} from "../services/auth-service";
import {City} from "../../shared/model/City";
import {CityApiService} from "../services/city-api-service";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import CryptoJS from 'crypto-js';




@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {

  formData = {cityId: '', name: '', lastName: '', emailAddress: '', password: '', confirmPassword: ''};
  cities: City [] = []
  registrationForm: FormGroup;
  passwordRegex = '^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#?$\\]]).{8,}$';


  constructor(private http: HttpClient, private router: Router, private authService: AuthService, private cityService: CityApiService) {
    this.loadCities()
    this.registrationForm = new FormGroup({
      cityId: new FormControl('', Validators.required),
      name: new FormControl('', [Validators.required, Validators.minLength(2)]),
      lastName: new FormControl('', [Validators.required, Validators.minLength(2)]),
      emailAddress: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', [
        Validators.required,
        Validators.pattern(this.passwordRegex)
      ]),
      confirmPassword: new FormControl('', Validators.required),
    });

  }



  loadCities() {
    this.cityService.getAllCities().subscribe(response => this.cities = response)
  }

  register() {

    if (this.formData.password !== this.formData.confirmPassword) {
      alert("Passwords do not match.");
      return;
    }

    let body = this.formData
    body.password = this.encryptString(body.password)
    body.confirmPassword = this.encryptString(body.confirmPassword)
    body.emailAddress = this.encryptString(body.emailAddress)

    this.authService.registerUser(body)
    this.router.navigate(['/login'])
  }

  checkIfEmailExists(email: string): boolean {
    return false;
  }

  encryptString(plainText: string): string {
    return CryptoJS.AES.encrypt(plainText, 'SECRET-KEY').toString();
  }
}



