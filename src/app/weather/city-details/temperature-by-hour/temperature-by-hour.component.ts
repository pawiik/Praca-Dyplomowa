import {Component, resolveForwardRef} from '@angular/core';
import {FallApiService} from "../../services/fall-api-service";

@Component({
  selector: 'app-temperature-by-hour',
  templateUrl: './temperature-by-hour.component.html',
  styleUrls: ['./temperature-by-hour.component.css']
})
export class TemperatureByHourComponent {

  hourlyWeather: {time:string, fall:string}[]=[]



  constructor(private fallService: FallApiService) {
    this.getData()
    this.fallService.getLast("10").subscribe(response => console.log(response))
  }

  getData(){
    this.fallService.getDayData("2024-01-06", "10").subscribe(response => {
      console.log(response)
        Object.entries(response).forEach(([key, value]) => {
          if(value != null){
            console.log(`Hour: ${key}, Value: ${value}`);
            let record = {time: key.toString(), fall: value.toString()}
            this.hourlyWeather.push(record)
          }
          else{
            let record = {time: key.toString(), fall: "No data"}
            this.hourlyWeather.push(record)
          }

        })
    }
    )}

}
