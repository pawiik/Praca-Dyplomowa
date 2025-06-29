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
  selector: 'app-actual-fall',
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
    console.log("getData")
    console.log(this.dataService.getData())
    this.getApiData()
  }
  cityId: number = this.dataService.getData().cityId
  data: Measurement = {
    temperature: "No data to display",
    fall: "No data to display",
    humidity: "No data to display",
    uv: "No data to display",
    wind: "No data to display"
  }

  private getApiData() {
    this.fallService.getLast(this.cityId.toString()).subscribe(response => this.data.fall = this.round(response.fall.valueOf()))
    this.temperatureService.getLast(this.cityId.toString()).subscribe(response => this.data.temperature = this.round(response.temperature.valueOf()))
    this.humidityService.getLast(this.cityId).subscribe(response => this.data.humidity = this.round(response.humidity.valueOf()))
    this.uvService.getLast(this.cityId.toString()).subscribe(response => this.data.uv = this.round(response.uv.valueOf()))
    this.WindService.getLast(this.cityId.toString()).subscribe(response => this.data.wind = this.round(response.wind.valueOf()))
  }

  private round(value: number): string {
    let rounded = Math.round((value + Number.EPSILON) * 10) / 10;
    return rounded.toString()
  }

}
