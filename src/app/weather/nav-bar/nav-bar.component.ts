import {Component, signal} from '@angular/core';
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
    this.router.navigate(['login']);
  }

  isAuthenticated(): boolean{
    return this.auth.isUserAuthenticated();
  }

  logout(): void{
    this.auth.logoutUser()
    this.router.navigate(['/home'])
  }

  showOffcanvas = false;
  goToMain(){
    this.router.navigate(['/home'])
  }

  toggleOffcanvas() {
    this.router.navigate(['user-information'])
  }

  goToEmployee() {
    this.router.navigate(['data-analysis'])
  }
}
