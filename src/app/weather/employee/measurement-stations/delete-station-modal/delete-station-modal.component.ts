import {Component, Inject} from '@angular/core';
import {MAT_DIALOG_DATA} from "@angular/material/dialog";
import {MeasurementStation} from "../../../../shared/model/MeasurementStation";
import {MeasurementStationApiService} from "../../../services/measurement-station-api-service";

@Component({
  selector: 'app-delete-station-modal',
  templateUrl: './delete-station-modal.component.html',
  styleUrls: ['./delete-station-modal.component.css']
})
export class DeleteStationModalComponent {

  constructor(@Inject(MAT_DIALOG_DATA) public data: MeasurementStation,
              private stationService: MeasurementStationApiService) {
  }

  submit(){
    this.stationService.deleteMeasurementStation(this.data.stationId.toString()).subscribe()
  }

}
