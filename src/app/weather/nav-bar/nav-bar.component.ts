import { Component } from '@angular/core';
import {Router} from "@angular/router";
import {AuthService} from "../services/auth-service";

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent {
  constructor(private router: Router, private auth: AuthService) {}

  goToLogin() {
    this.router.navigate(['login']); // Assuming '/login' is the route for your login form
  }

  isAuthenticated(): boolean{
    return this.auth.isUserAuthenticated();
  }

  logout(): void{
    this.auth.logoutUser()
  }

  showOffcanvas = false;

  toggleOffcanvas() {
    this.router.navigate(['user-information'])
  }

}
