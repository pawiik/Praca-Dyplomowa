import { Component } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {

  formData = {cityId:'', name: '', lastName: '', emailAddress: '', password: '', confirmPassword: '' };

  constructor(private http: HttpClient) {}

  register() {

    if (this.formData.password !== this.formData.confirmPassword) {
      alert("Passwords do not match.");
      return;
    }



    let body = this.formData



    this.http.post('http://localhost:8080/auth/register', body)
      .subscribe({
        next: (response) => {
          console.log('Registration successful', response);
        },
        error: (error) => {
          console.error('Registration failed', error);
        }
      });
  }
}



