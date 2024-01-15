import { Component } from '@angular/core';
import {Region} from "../../../shared/model/Region";
import {AlertApiService} from "../../services/alert-api-service";
import {RegionApiService} from "../../services/region-api-service";
import {FormControl, FormGroup, Validators} from "@angular/forms";


@Component({
  selector: 'app-add-alert-modal',
  templateUrl: './add-alert-modal.component.html',
  styleUrls: ['./add-alert-modal.component.css']
})
export class AddAlertModalComponent {

  constructor(private alertService: AlertApiService,
              private regionService: RegionApiService) {
    this.loadRegions()
  }

  regions: Region[] = []

  form!: FormGroup;

  ngOnInit() {
    this.form = new FormGroup({
      startTime: new FormControl(null, Validators.required),
      endTime: new FormControl(null, Validators.required),
      region: new FormControl(null, Validators.required),
      message: new FormControl('', Validators.required),
      alertType: new FormControl(null, Validators.required)
    });
  }


  onSubmit(){
    console.log(this.form.value)
    this.alertService.addNewAlert(this.form.value).subscribe()
  }

  private loadRegions() {
    this.regionService.getAllRegions().subscribe(response => this.regions = response)
  }
}
