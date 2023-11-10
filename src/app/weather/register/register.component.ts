import { Component } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {

  user: User = { name: '', lastName: '', email: '', password: '', confirmPassword: '' };

  constructor(private http: HttpClient) {}

  register() {
    if (this.user.password !== this.user.confirmPassword) {
      alert("Passwords do not match.");
      return;
    }

    this.http.post('https://your-server.com/api/register', this.user)
      .subscribe({
        next: (response) => {
          console.log('Registration successful', response);
          // Handle successful registration
        },
        error: (error) => {
          console.error('Registration failed', error);
          // Handle registration error
        }
      });
  }
}

class User{
  name: string | undefined;
  lastName: string | undefined;
  email: string | undefined;
  password: string | undefined;
  confirmPassword: string | undefined;

}

