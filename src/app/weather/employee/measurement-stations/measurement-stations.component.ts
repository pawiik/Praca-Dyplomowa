import {Component, signal} from '@angular/core';
import {MeasurementStationApiService} from "../../services/measurement-station-api-service";
import {MeasurementStation} from "../../../shared/model/MeasurementStation";
import {ModifyCityModalComponent} from "../cities/modify-city-modal/modify-city-modal.component";
import {MatDialog} from "@angular/material/dialog";
import {AddCityModalComponent} from "../cities/add-city-modal/add-city-modal.component";
import {
  AddMeasurementStationModalComponent
} from "./add-measurement-station-modal/add-measurement-station-modal.component";
import {ModifyStationModalComponent} from "./modify-station-modal/modify-station-modal.component";
import {DeleteStationModalComponent} from "./delete-station-modal/delete-station-modal.component";

@Component({
  selector: 'app-measurement-stations',
  templateUrl: './measurement-stations.component.html',
  styleUrls: ['./measurement-stations.component.css']
})
export class MeasurementStationsComponent {

  stations: MeasurementStation[] = []


  constructor(private measurementStationService: MeasurementStationApiService,
              private dialog: MatDialog) {
    this.loadStations()
  }


  loadStations(){
    this.measurementStationService.getAllMeasurementStations().subscribe(response => this.stations = response)
  }

  openAddModal(){
    const dialogRef = this.dialog.open(AddMeasurementStationModalComponent, {
      width: '40%',
      panelClass: 'custom-mat-dialog-container',
    });

    dialogRef.afterClosed().subscribe(result => {
      this.loadStations()

    });

  }

  openModifyModal(station: MeasurementStation){
    const dialogRef = this.dialog.open(ModifyStationModalComponent, {
      width: '40%',
      panelClass: 'custom-mat-dialog-container',
      data: station
    });

    dialogRef.afterClosed().subscribe(result => {
      this.loadStations()
    });
  }

  openDeleteModal(station: MeasurementStation){
    const dialogRef = this.dialog.open(DeleteStationModalComponent, {
      width: '40%',
      panelClass: 'custom-mat-dialog-container',
      data: station
    });

    dialogRef.afterClosed().subscribe(result => {
      this.measurementStationService.deleteMeasurementStation(station.stationId.toString())
      this.loadStations()

    });
  }

}
