import { Component } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Region} from "../../../../shared/model/Region";
import {City} from "../../../../shared/model/City";
import {MeasurementStationApiService} from "../../../services/measurement-station-api-service";
import {RegionApiService} from "../../../services/region-api-service";
import {CityApiService} from "../../../services/city-api-service";

@Component({
  selector: 'app-add-region-modal',
  templateUrl: './add-region-modal.component.html',
  styleUrls: ['./add-region-modal.component.css']
})
export class AddRegionModalComponent {
  form!: FormGroup;

  constructor(private regionService: RegionApiService) {
  }




  ngOnInit() {
    this.form = new FormGroup({
      regionName: new FormControl("", Validators.required),
    });
  }


  onSubmit(){

    this.regionService.addNewRegion(this.form.value).subscribe()
  }
}
