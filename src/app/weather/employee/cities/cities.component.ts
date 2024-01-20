import { Component } from '@angular/core';
import {City} from "../../../shared/model/City";
import {CityApiService} from "../../services/city-api-service";
import {MatDialog} from "@angular/material/dialog";
import {AddAlertModalComponent} from "../../alerts/add-alert-modal/add-alert-modal.component";
import {AddCityModalComponent} from "./add-city-modal/add-city-modal.component";
import {ModifyAlertModalComponent} from "../../alerts/modify-alert-modal/modify-alert-modal.component";
import {ModifyCityModalComponent} from "./modify-city-modal/modify-city-modal.component";

@Component({
  selector: 'app-cities',
  templateUrl: './cities.component.html',
  styleUrls: ['./cities.component.css']
})
export class CitiesComponent {

  cities: City[] = []

  constructor(private cityService: CityApiService,
              private dialog: MatDialog) {
    this.loadCities()
  }

  private loadCities() {
    this.cityService.getAllCities().subscribe(response => this.cities = response)
  }

  openAddCityModal(){
    const dialogRef = this.dialog.open(AddCityModalComponent, {
      width: '40%',
      panelClass: 'custom-mat-dialog-container'
    });

    dialogRef.afterClosed().subscribe(result => {
    });
  }

  openModifyCityModal(city: City){
    const dialogRef = this.dialog.open(ModifyCityModalComponent, {
      width: '40%',
      panelClass: 'custom-mat-dialog-container',
      data: city
    });

    dialogRef.afterClosed().subscribe(result => {
    });
  }

}
