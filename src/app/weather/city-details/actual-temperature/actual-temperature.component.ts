import {Component, Input, resolveForwardRef} from '@angular/core';
import {DataService} from "../city-details.component";
import {FallApiService} from "../../services/fall-api-service";
import {TemperatureApiService} from "../../services/temperature-api-service";

@Component({
  selector: 'app-actual-temperature',
  templateUrl: './actual-temperature.component.html',
  styleUrls: ['./actual-temperature.component.css']
})
export class ActualTemperatureComponent {

  constructor(private dataService: DataService,
              private fallService: FallApiService,
              private temperatureService: TemperatureApiService
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
    this.temperatureService.getLast(this.cityId).subscribe(response => {
      let value = response.temperature.valueOf()
      let rounded = Math.round((value + Number.EPSILON) * 10) / 10;
      this.data.temperature = rounded.toString()
    } )
  }

}
