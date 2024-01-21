import {Component, Inject} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Region} from "../../../../shared/model/Region";
import {MAT_DIALOG_DATA} from "@angular/material/dialog";
import {City} from "../../../../shared/model/City";
import {CityApiService} from "../../../services/city-api-service";
import {RegionApiService} from "../../../services/region-api-service";
import {MeasurementStation} from "../../../../shared/model/MeasurementStation";
import {MeasurementStationApiService} from "../../../services/measurement-station-api-service";

@Component({
  selector: 'app-modify-station-modal',
  templateUrl: './modify-station-modal.component.html',
  styleUrls: ['./modify-station-modal.component.css']
})
export class ModifyStationModalComponent {
  form!: FormGroup;
  cities: City[] = []

  constructor(@Inject(MAT_DIALOG_DATA) public data: MeasurementStation,
              private stationService: MeasurementStationApiService,
              private cityService: CityApiService) {
    this.loadCities()
  }

  loadCities(){
    this.cityService.getAllCities().subscribe(response => this.cities = response)
  }



  ngOnInit() {
    this.form = new FormGroup({
      address: new FormControl(this.data.address, Validators.required),
      cityId: new FormControl(this.data.city?.cityId, Validators.required),
    });
  }


  onSubmit(){
    console.log(this.form.value)
    let stationId = this.data.stationId
    let body = {
      stationId: stationId,
      address: this.form.value.address,
      cityId: this.form.value.cityId
    }
    this.stationService.modifyMeasurementStation(body).subscribe()
  }
}
