import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {User} from "../../shared/model/User";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  authData = {username: '', userRole: '', jwtToken: ''}

  constructor(private httpClient: HttpClient) {}

  authenticateUser(userData: any): void {
    localStorage.setItem('user', JSON.stringify(userData));
  }

  isUserAuthenticated(): boolean {
    return localStorage.getItem('user') !== null;
  }

  getUserData(): any {
    return JSON.parse(localStorage.getItem('authData') || '{}');
  }

  logoutUser(): void {
    localStorage.removeItem('user');
  }

  loginUser(body: {}){
    this.httpClient.post<any>('http://localhost:8080/auth/login', body)
      .subscribe({
        next: (response) => {
          console.log('Login successful', response);
          this.authData.username = response.user.userId
          this.authData.jwtToken = response.jwtToken
          this.authData.userRole = response.role.authority

          this.authenticateUser(this.authData);
        },
        error: (error) => {
          console.error('Login failed', error);
        }
      });
  }

  registerUser(body: {}): void{
    this.httpClient.post<any>('http://localhost:8080/auth/register', body)
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
