import {Component, Input} from '@angular/core';
import {DataService} from "../city-details.component";
import {FallApiService} from "../../services/fall-api-service";

@Component({
  selector: 'app-actual-temperature',
  templateUrl: './actual-temperature.component.html',
  styleUrls: ['./actual-temperature.component.css']
})
export class ActualTemperatureComponent {

  constructor(private dataService: DataService,
              private fallService: FallApiService,
              ) {
    this.getApiData()
  }
  cityId: string = "10"
  data: {temperature: string, fall: string} = {
    temperature: "No data to display",
    fall: "No data to display"
  }

  private getApiData() {
    this.fallService.getLast(this.cityId).subscribe(response => this.data.fall = response.temperature.toString())
  }
}
