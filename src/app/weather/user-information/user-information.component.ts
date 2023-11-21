import {Component, Input} from '@angular/core';
import {User} from "../../shared/model/User";
import {UserService} from "../services/user-service";
import {AuthService} from "../services/auth";
import {FormControl, FormGroup} from "@angular/forms";
import {City} from "../../shared/model/City";
import {Alert} from "../../shared/model/Alert";

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
  userCities: City[] = []
  userAlerts: Alert[] = []
  constructor(private userService: UserService, private authService: AuthService) {
    this.userForm = new FormGroup({
      userId: new FormControl(''),
      name: new FormControl(''),
      lastName: new FormControl(''),
      phoneNumber: new FormControl(0),
      address: new FormControl(''),
      emailAddress: new FormControl(''),
    });
    this.loadUser()
  }
  loadUser(){
    let username = this.authService.authData.username;
    console.log(username)
    this.userService.getUserInformation(username).subscribe(response => {
      console.log("response")
      console.log(response)
      this.user = response;

      this.userForm.patchValue({
        userId: this.user.userId,
        name: this.user.name,
        lastName: this.user.lastName,
        phoneNumber: this.user.phoneNumber,
        address: this.user.address,
        emailAddress: this.user.emailAddress
      });
      console.log("form value")
      console.log(this.userForm.value)
    });

  }

  showOffcanvas = false;
  toggleOffcanvas() {
    this.showOffcanvas = !this.showOffcanvas;
  }

  // updateUserData(){
  //   let group: any = {};
  //   console.log("updating")
  //   Object.keys(this.user).forEach(key => {
  //     // @ts-ignore
  //     group[key] = new FormControl(this.userFormData[key]);
  //   });
  //   // group["phoneNumber"] = +group["phoneNumber"]
  //   this.userForm = new FormGroup(group)
  //   console.log(this.userForm.value)
  // }

  updateData(){
    console.log(this.userForm.value)
    // this.updateUserData()
    this.userService.updateUserInformation(this.userForm.value).subscribe(user => this.user = user)
    console.log(this.user)
  }

}
