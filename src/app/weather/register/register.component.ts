import { Component } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Router} from "@angular/router";
import {AuthService} from "../services/auth";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {

  formData = {cityId:'', name: '', lastName: '', emailAddress: '', password: '', confirmPassword: '' };

  constructor(private http: HttpClient, private router: Router, private authService: AuthService) {}

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



