import { Component } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Router } from "@angular/router";
import { AuthService } from "../services/auth-service";
import CryptoJS from "crypto-js";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  constructor(private httpClient: HttpClient, private router: Router, private authService: AuthService) { }

  formData = { emailAddress: "", password: "" };
  loginError = false;
  missingData = false;

  login() {
    if (this.formData.emailAddress && this.formData.password) {
      this.missingData = false;

      let password = this.formData.password;
      let emailAddress = this.formData.emailAddress;

      let body = {
        password: this.encryptString(password),
        emailAddress: this.encryptString(emailAddress)
      };

      this.authService.loginUser(body);
      if (this.authService.isUserAuthenticated()) {
        this.router.navigate(['/home']);
        this.loginError = false;
      } else {
        this.loginError = true;
      }
    } else {
      this.missingData = true;
    }
  }

  encryptString(plainText: string): string {
    const key = CryptoJS.enc.Utf8.parse('b14ca5898a4e4133bbce2ea2315a1916');
    const iv = CryptoJS.enc.Utf8.parse('some16byteString');
    const encrypted = CryptoJS.AES.encrypt(plainText, key, { iv: iv, mode: CryptoJS.mode.CBC, padding: CryptoJS.pad.Pkcs7 });
    return encrypted.toString();
  }
}
