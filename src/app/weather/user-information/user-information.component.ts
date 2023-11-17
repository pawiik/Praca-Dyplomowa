import {Component, Input} from '@angular/core';
import {User} from "../../shared/model/User";
import {UserService} from "../services/user-service";
import {AuthService} from "../services/auth";
import {FormControl, FormGroup} from "@angular/forms";

@Component({
  selector: 'user-information',
  templateUrl: './user-information.component.html',
  styleUrls: ['./user-information.component.css']
})
export class UserInformationComponent {
  @Input() isActive: boolean = false;

  userForm: FormGroup;
  user: User = new User('', '', '', '', 0, '', '', [], null);
  userFormData = {"id": "", "name": "", "lastName": "", "phoneNumber": "", "address": "", "emailAddress": ""}
  constructor(private userService: UserService, private authService: AuthService) {
    this.userForm = new FormGroup({
      userId: new FormControl(''),
      name: new FormControl(''),
      lastName: new FormControl(''),
      phoneNumber: new FormControl(''),
      address: new FormControl(''),
      email: new FormControl(''),
    });
    this.loadUser()
  }
  loadUser(){
    let username = this.authService.authData.username;
    this.userService.getUserInformation(username).subscribe(response => {
      this.user = response;
      this.userForm.patchValue(this.user); // Use patchValue to update the form
    });

  }

  showOffcanvas = false;
  toggleOffcanvas() {
    this.showOffcanvas = !this.showOffcanvas;
  }

  updateUserData(username: any){
    let group: any = {};
    Object.keys(this.user).forEach(key => {
      // @ts-ignore
      group[key] = new FormControl(this.userFormData[key]);
    });
    this.userForm = new FormGroup(group)
  }

  updateData(){
    console.log(this.user)
  }

}
