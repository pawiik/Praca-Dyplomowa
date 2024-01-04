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
    this.fallApiService.loadByTimePeriod(body).subscribe(
      falls =>
        falls.forEach(fall =>{
          console.log(fall)
          let measurement = new Measurement(fall.measurementStationId.stationId, fall.time, fall.temperature, "")
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
        })
    )
  }

  onSubmit() {
    // console.log({
    //   region: this.region,
    //   startDate: this.startDate,
    //   endDate: this.endDate,
    //   parameter: this.parameter,
    //   sort: this.sort
    // });
    let body:{} =
      {
        "regionId": 16,
        "startTime": "2024-01-01T16:47:00",
        "endTime": "2024-01-03T16:47:00"
      }
    //   {
    //   "regionId": this.region?.toString(),
    //   "startTime": this.startDate?.toString(),
    //   "endTime": this.endDate?.toString()
    // }

    if(this.parameter == "wind"){
      this.loadWinds(body)
    }
    else if(this.parameter == "fall"){
      console.log(body)
      this.loadFalls(body)
    }
    else if(this.parameter == "humidity"){
      this.loadHumidity(body)
    }
    else if(this.parameter == "temperature"){
      this.loadTemperatures(body)
    }
    else if(this.parameter == "uv"){
      this.loadUV(body)
    }
  }

}
class Measurement{
  measurementStationId: number
  date: number
  value: number
  markers: string


  constructor(measurementStation: number, date: number, value: number, markers: string) {
    this.measurementStationId = measurementStation;
    this.date = date;
    this.value = value;
    this.markers = markers;
  }
}
