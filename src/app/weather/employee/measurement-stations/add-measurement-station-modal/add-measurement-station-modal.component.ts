import { Component } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Region} from "../../../../shared/model/Region";
import {CityApiService} from "../../../services/city-api-service";
import {RegionApiService} from "../../../services/region-api-service";
import {MeasurementStationApiService} from "../../../services/measurement-station-api-service";
import {City} from "../../../../shared/model/City";
import {
  logBuilderStatusWarnings
} from "@angular-devkit/build-angular/src/builders/browser-esbuild/builder-status-warnings";

@Component({
  selector: 'app-add-measurement-station-modal',
  templateUrl: './add-measurement-station-modal.component.html',
  styleUrls: ['./add-measurement-station-modal.component.css']
})
export class AddMeasurementStationModalComponent {
  form!: FormGroup;
  regions: Region[] = []
  cities: City[] = []

  constructor(private stationService: MeasurementStationApiService,
              private regionService: RegionApiService,
              private cityService: CityApiService) {
    this.loadCities()
  }

  loadCities(){
    this.cityService.getAllCities().subscribe(response => this.cities = response)
  }



  ngOnInit() {
    this.form = new FormGroup({
      cityId: new FormControl("", Validators.required),
      address: new FormControl("", Validators.required),
    });
  }


  onSubmit(){
    console.log(this.form.value)

    this.stationService.addNewHumidity(this.form.value).subscribe(response => console.log(response))
  }
}
