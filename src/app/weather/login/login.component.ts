import { Component } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
    constructor(private httpClient: HttpClient ) {
    }

    formData = {emailAddress: "", password: ""}

    login(){
        if (this.formData.emailAddress != null && this.formData.password != null){
          this.httpClient.post('http://localhost:8080/auth/login', this.formData)
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
}
