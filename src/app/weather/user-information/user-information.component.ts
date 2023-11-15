import {Component, Input} from '@angular/core';
import {User} from "../../shared/model/User";
import {UserService} from "../services/user-service";
import {AuthService} from "../services/auth";

@Component({
  selector: 'user-information',
  templateUrl: './user-information.component.html',
  styleUrls: ['./user-information.component.css']
})
export class UserInformationComponent {
  @Input() isActive: boolean = false;

  user: User = new User('', '', '', '', 0, '', '', [], null);

  constructor(private userService: UserService, private authService: AuthService) {
    this.loadUser()
  }
  loadUser(){
    let username = this.authService.authData.username
    this.userService.getUserInformation(username).subscribe(response => this.user = response);
    console.log(this.user)
  }

  showOffcanvas = false;
  toggleOffcanvas() {
    this.showOffcanvas = !this.showOffcanvas;
  }

  updateData(){
    console.log(this.user)
  }

}
