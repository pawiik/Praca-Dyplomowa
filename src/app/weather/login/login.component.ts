import { Component } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {AuthService} from "../services/auth-service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
    constructor(private httpClient: HttpClient, private router: Router, private authService: AuthService) {
    }

    formData = {emailAddress: "", password: ""}
    loginError = false;
    missingData = false

    login(){
        if (this.formData.emailAddress != null && this.formData.password != null){
          this.missingData = false
          this.authService.loginUser(this.formData)
          if(this.authService.isUserAuthenticated()){
            this.router.navigate(['/home'])
            this.loginError = false
          }
          else{
            this.loginError = true
          }

        }
        else{
          this.missingData = true
        }

        }
}
