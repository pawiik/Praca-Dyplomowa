import {Component, Input, resolveForwardRef} from '@angular/core';
import {DataService} from "../city-details.component";
import {FallApiService} from "../../services/fall-api-service";
import {TemperatureApiService} from "../../services/temperature-api-service";
import {HumidityApiService} from "../../services/humidity-api-service";
import {UvApiService} from "../../services/uv-api-service";
import {WindApiService} from "../../services/wind-api-service";


interface Measurement{
  temperature: string,
  fall: string,
  humidity: string,
  uv: string,
  wind: string
}

@Component({
  selector: 'app-actual-temperature',
  templateUrl: './actual-temperature.component.html',
  styleUrls: ['./actual-temperature.component.css']
})
export class ActualTemperatureComponent {

  constructor(private dataService: DataService,
              private fallService: FallApiService,
              private temperatureService: TemperatureApiService,
              private humidityService: HumidityApiService,
              private uvService: UvApiService,
              private WindService: WindApiService
              ) {
    this.getApiData()
  }
  cityId: string = "10"
  data: Measurement = {
    temperature: "No data to display",
    fall: "No data to display",
    humidity: "No data to display",
    uv: "No data to display",
    wind: "No data to display"
  }

  private getApiData() {
    this.fallService.getLast(this.cityId).subscribe(response => this.data.fall = response.temperature.toString())
    this.temperatureService.getLast(this.cityId).subscribe(response => {
      let value = response.temperature.valueOf()
      let rounded = Math.round((value + Number.EPSILON) * 10) / 10;
      this.data.temperature = rounded.toString()
    } )
    this.humidityService.getLast(this.cityId).subscribe(response => this.data.humidity = response.humidity.toString())
    this.uvService.getLast(this.cityId).subscribe(response => this.data.wind = response.uv.toString())
  }

}
