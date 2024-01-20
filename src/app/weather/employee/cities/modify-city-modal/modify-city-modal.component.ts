import {Component, Inject} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Region} from "../../../../shared/model/Region";
import {CityApiService} from "../../../services/city-api-service";
import {RegionApiService} from "../../../services/region-api-service";
import {MAT_DIALOG_DATA} from "@angular/material/dialog";
import {Alert} from "../../../../shared/model/Alert";

@Component({
  selector: 'app-modify-city-modal',
  templateUrl: './modify-city-modal.component.html',
  styleUrls: ['./modify-city-modal.component.css']
})
export class ModifyCityModalComponent {

  form!: FormGroup;
  regions: Region[] = []

  constructor(@Inject(MAT_DIALOG_DATA) public data: Alert,
              private cityService: CityApiService,
              private regionService: RegionApiService) {
    this.loadRegions()
  }

  loadRegions(){
    this.regionService.getAllRegions().subscribe(response => this.regions = response)
  }



  ngOnInit() {
    this.form = new FormGroup({
      cityName: new FormControl("", Validators.required),
      regionId: new FormControl(null, Validators.required),
    });
  }


  onSubmit(){
    console.log(this.form.value)

    this.cityService.addNewCity(this.form.value).subscribe()
  }
}
