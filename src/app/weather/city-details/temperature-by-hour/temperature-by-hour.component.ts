import {Component, resolveForwardRef} from '@angular/core';
import {FallApiService} from "../../services/fall-api-service";
import {TemperatureApiService} from "../../services/temperature-api-service";
import {HumidityApiService} from "../../services/humidity-api-service";
import {UvApiService} from "../../services/uv-api-service";
import {WindApiService} from "../../services/wind-api-service";


interface HourlyWeather {
  time: string;
  fall: string;
  temperature: string;
  humidity: string;
  uv: string;
  wind: string;
}

@Component({
  selector: 'app-temperature-by-hour',
  templateUrl: './temperature-by-hour.component.html',
  styleUrls: ['./temperature-by-hour.component.css']
})
export class TemperatureByHourComponent {

  hourlyWeather: HourlyWeather[]=[]
  date: string = "2024-01-06"
  cityId: string = "10"



  constructor(private fallService: FallApiService,
              private temperatureService: TemperatureApiService,
              private humidityService: HumidityApiService,
              private uvService: UvApiService,
              private windService: WindApiService) {
    this.getData()
    this.fallService.getLast(this.cityId).subscribe(response => console.log(response))
  }

  getData(){
    this.fallService.getDayData(this.date, this.cityId).subscribe(response => {
      console.log(response)
        Object.entries(response).forEach(([key, value]) => {
          if(value != null){
            console.log(`Hour: ${key}, Value: ${value}`);
            let record = {time: key.toString(), fall: value.toString(), temperature:"No data", humidity: "No data", uv: "No data", wind: "No data"}
            this.hourlyWeather.push(record)
          }
          else{
            let record = {time: key.toString(), fall: "No data", temperature: "No data", humidity: "No data", uv: "No data", wind: "No data"}
            this.hourlyWeather.push(record)
          }
        })
    })
    this.temperatureService.getDayData(this.date, this.cityId).subscribe(response =>{
        Object.entries(response).forEach(([key, value])=> {
          if(value != null){
            const weather = this.hourlyWeather.find(weather => weather.time === key);
            if (weather) {
              weather.temperature = value;
            } else {
              console.log('Element not found');
            }
          }
        })
    })
    this.humidityService.getDayData(this.date, this.cityId).subscribe(response =>{
      Object.entries(response).forEach(([key, value])=> {
        if(value != null){
          const weather = this.hourlyWeather.find(weather => weather.time === key);
          if (weather) {
            weather.humidity = value;
          } else {
            console.log('Element not found');
          }
        }
      })
    })
    this.uvService.getDayData(this.date, this.cityId).subscribe(response => {
      Object.entries(response).forEach(([key, value])=> {
        if(value != null){
          const weather = this.hourlyWeather.find(weather => weather.time === key);
          if (weather) {
            weather.uv = value;
          } else {
            console.log('Element not found');
          }
        }
      })
    })
    this.windService.getDayData(this.date, this.cityId).subscribe(response => {
      Object.entries(response).forEach(([key, value])=> {
        if(value != null){
          const weather = this.hourlyWeather.find(weather => weather.time === key);
          if (weather) {
            weather.wind = value;
          } else {
            console.log('Element not found');
          }
        }
      })
    })
  }
}
