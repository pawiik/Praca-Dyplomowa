import { Component } from '@angular/core';
import {AuthService} from "../services/auth-service";
import {FallApiService} from "../services/fall-api-service";
import {WindApiService} from "../services/wind-api-service";
import {TemperatureApiService} from "../services/temperature-api-service";
import {UvApiService} from "../services/uv-api-service";
import {HumidityApiService} from "../services/humidity-api-service";

@Component({
  selector: 'app-measurements-table',
  templateUrl: './measurements-table.component.html',
  styleUrls: ['./measurements-table.component.css']
})
export class MeasurementsTableComponent {

  constructor(private authService: AuthService,
              private fallApiService: FallApiService,
              private windApiService: WindApiService,
              private temperatureApiService: TemperatureApiService,
              private uvApiService: UvApiService,
              private humidityApiService: HumidityApiService
  ) {
  }
  region: string | undefined;
  startDate: string | undefined;
  endDate: string | undefined;
  parameter: string | undefined;
  sort: string | undefined;
  measurementsTable:  Measurement[] = [];


  loadFalls(body:{}){
    this.fallApiService.loadByTimePeriod(body).subscribe(
      falls =>
        falls.forEach(fall =>{
          let measurement = new Measurement(fall.measurementStation.stationId, fall.time, fall.temperature, "")
        })
    )}
  loadWinds(body: {}){
    this.windApiService.loadByTimePeriod(body).subscribe(
      winds =>
        winds.forEach(wind =>{
          let measurement = new Measurement(wind.measurementStation.stationId, wind.time, wind.wind, "")
        })
    )
  }

  loadTemperatures(body: {}){
    this.temperatureApiService.loadByTimePeriod(body).subscribe(
      temperatures =>
        temperatures.forEach(temperature =>{
          let measurement = new Measurement(temperature.measurementStation.stationId, temperature.time, temperature.temperature, "")
        })
    )
  }

  loadUV(body: {}){
    this.uvApiService.loadByTimePeriod(body).subscribe(
      uvs =>
        uvs.forEach(uv =>{
          let measurement = new Measurement(uv.measurementStation.stationId, uv.time, uv.uv, "")
        })
    )
  }

  loadHumidity(body: {}){
    this.humidityApiService.loadByTimePeriod(body).subscribe(
      humidities =>
        humidities.forEach(humidity =>{
          let measurement = new Measurement(humidity.measurementStation.stationId, humidity.time, humidity.humidity, "")
        })
    )
  }

  onSubmit() {
    console.log({
      region: this.region,
      startDate: this.startDate,
      endDate: this.endDate,
      parameter: this.parameter,
      sort: this.sort
    });
    if(this.parameter == "wind"){
      this.loadWinds({})
    }
    else if(this.parameter == "fall"){
      this.loadFalls({})
    }
    else if(this.parameter == "humidity"){
      this.loadHumidity({})
    }
    else if(this.parameter == "temperature"){
      this.loadTemperatures({})
    }
    else if(this.parameter == "uv"){
      this.loadUV({})
    }
  }

}
class Measurement{
  measurementStation: number
  date: number
  value: number
  markers: string


  constructor(measurementStation: number, date: number, value: number, markers: string) {
    this.measurementStation = measurementStation;
    this.date = date;
    this.value = value;
    this.markers = markers;
  }
}
