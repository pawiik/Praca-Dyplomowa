import { Component } from '@angular/core';
import {City} from "../../../shared/model/City";
import {CityApiService} from "../../services/city-api-service";

@Component({
  selector: 'app-cities',
  templateUrl: './cities.component.html',
  styleUrls: ['./cities.component.css']
})
export class CitiesComponent {

  cities: City[] = []

  constructor(private cityService: CityApiService) {
    this.loadCities()
  }

  private loadCities() {
    this.cityService.getAllCities().subscribe(response => this.cities = response)
  }

  openAddModal(){

  }

}
