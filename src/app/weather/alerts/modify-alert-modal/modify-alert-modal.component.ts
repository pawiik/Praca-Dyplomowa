import { Component, Inject } from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {Alert} from "../../../shared/model/Alert";
import {AlertApiService} from "../../services/alert-api-service";
import {FormControl, FormGroup} from "@angular/forms";
import {User} from "../../../shared/model/User";
import {City} from "../../../shared/model/City";
import {UserApiService} from "../../services/user-api-service";
import {AuthService} from "../../services/auth-service";
import {Region} from "../../../shared/model/Region";
import {RegionApiService} from "../../services/region-api-service";
import moment from "moment";
import {AddAlertModalComponent} from "../add-alert-modal/add-alert-modal.component";


@Component({
  selector: 'app-modify-alert-modal',
  templateUrl: './modify-alert-modal.component.html',
  styleUrls: ['./modify-alert-modal.component.css']
})
export class ModifyAlertModalComponent {

  constructor(@Inject(MAT_DIALOG_DATA) public data: Alert,
              public dialogRef: MatDialogRef<AddAlertModalComponent>,
              private regionService: RegionApiService,
              private alertService: AlertApiService) {
    this.userForm = new FormGroup({
        alertId: new FormControl(data.alertId),
        startTime: new FormControl(new Date(data.startTime).toISOString().slice(0, 16)),
        endTime: new FormControl(new Date(data.startTime).toISOString().slice(0, 16)),
        region: new FormControl(data.region.regionId),
        message: new FormControl(data.message),
        alertType: new FormControl(data.alertType),
      });
    console.log(this.userForm.value)
    this.loadRegions()
    console.log("hello")
    console.log(data)
    this.loadUser()
  }


  userForm: FormGroup;
  userCities: City[] = []
  userAlerts: Alert[] = []
  regions: Region[] = []

  loadUser(){
      // this.userForm.patchValue({
      //   startTime: this.data.startTime,
      //   endTime: this.data.endTime,
      //   region: this.data.region.name,
      //   message: this.data.message,
      //   alertType: this.data.alertType
      // });
      // console.log("form value")
      // console.log(this.userForm.value)

  }
  updateData(){
    this.alertService.modifyAlert(this.userForm.value).subscribe()
  }

  loadRegions(){
    this.regionService.getAllRegions().subscribe(response => this.regions = response)
  }

  saveChanges(){
    this.dialogRef.close("aa");
    this.alertService.modifyAlert(this.userForm.value)
  }
}
