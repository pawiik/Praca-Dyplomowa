import { Component } from '@angular/core';
import {AuthService} from "../services/auth-service";
import {FallApiService} from "../services/fall-api-service";
import {WindApiService} from "../services/wind-api-service";
import {TemperatureApiService} from "../services/temperature-api-service";
import {UvApiService} from "../services/uv-api-service";
import {HumidityApiService} from "../services/humidity-api-service";
import {Region} from "../../shared/model/Region";
import {RegionApiService} from "../services/region-api-service";

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
              private humidityApiService: HumidityApiService,
              private regionApiService: RegionApiService
  ) {
    this.loadRegions()
  }
  regions: Region[] = []

  region: string | undefined;
  startDate: string | undefined;
  endDate: string | undefined;
  parameter: string | undefined;
  sort: string | undefined;

  measurementsTable:  Measurement[] = [];

  loadRegions(){
    this.regionApiService.getAllRegions().subscribe(response => this.regions = response)
  }

  loadFalls(body:{}){
    console.log("load falls")
    this.fallApiService.loadByTimePeriod(body).subscribe(
      falls =>
        falls.forEach(fall =>{
          console.log(fall)
          let measurement = new Measurement(fall.measurementStationId, fall.time, fall.temperature, "")
          this.measurementsTable.push(measurement)
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
          let measurement = new Measurement(uv.measurementStationId.stationId, uv.time, uv.uv, "")
        })
    )
  }

  loadHumidity(body: {}){
    this.humidityApiService.loadByTimePeriod(body).subscribe(
      humidities =>
        humidities.forEach(humidity =>{
          let measurement = new Measurement(humidity.measurementStation.stationId, humidity.time, humidity.humidity, "")
          this.measurementsTable.push(measurement)
        })
    )
  }

  onSubmit() {
    let body = {
      "regionId": this.region,
      "startTime": this.startDate,
      "endTime": this.endDate
    };
    this.measurementsTable = []
    switch(this.parameter) {
      case "wind":
        this.loadWinds(body);
        break;
      case "fall":
        this.loadFalls(body);
        break;
      case "humidity":
        this.loadHumidity(body);
        break;
      case "temperature":
        this.loadTemperatures(body);
        break;
      case "uv":
        this.loadUV(body);
        break;
      default:
    }
  }

}
class Measurement{
  measurementStationId: number
  date: number
  value: number
  markers: string


  constructor(measurementStation: any, date: number, value: number, markers: string) {
    this.measurementStationId = measurementStation;
    this.date = date;
    this.value = value;
    this.markers = markers;
  }
}
