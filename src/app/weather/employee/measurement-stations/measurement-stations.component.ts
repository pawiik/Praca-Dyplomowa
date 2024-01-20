import {Component, signal} from '@angular/core';
import {MeasurementStationApiService} from "../../services/measurement-station-api-service";
import {MeasurementStation} from "../../../shared/model/MeasurementStation";

@Component({
  selector: 'app-measurement-stations',
  templateUrl: './measurement-stations.component.html',
  styleUrls: ['./measurement-stations.component.css']
})
export class MeasurementStationsComponent {

  stations: MeasurementStation[] = []


  constructor(private measurermentStationService: MeasurementStationApiService) {
    this.loadStations()
  }


  loadStations(){
    this.measurermentStationService.getAllHumidity().subscribe(response => this.stations = response)
  }

  openAddModal(){

  }

}
