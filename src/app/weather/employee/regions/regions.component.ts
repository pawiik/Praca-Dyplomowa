import { Component } from '@angular/core';
import {
  AddMeasurementStationModalComponent
} from "../measurement-stations/add-measurement-station-modal/add-measurement-station-modal.component";
import {MeasurementStation} from "../../../shared/model/MeasurementStation";
import {ModifyStationModalComponent} from "../measurement-stations/modify-station-modal/modify-station-modal.component";
import {DeleteStationModalComponent} from "../measurement-stations/delete-station-modal/delete-station-modal.component";
import {RegionApiService} from "../../services/region-api-service";
import {MatDialog} from "@angular/material/dialog";
import {Region} from "../../../shared/model/Region";
import {DeleteRegionModalComponent} from "./delete-region-modal/delete-region-modal.component";
import {ModifyRegionModalComponent} from "./modify-region-modal/modify-region-modal.component";
import {AddRegionModalComponent} from "./add-region-modal/add-region-modal.component";

@Component({
  selector: 'app-regions',
  templateUrl: './regions.component.html',
  styleUrls: ['./regions.component.css']
})
export class RegionsComponent {

  regions: Region[] = []

  constructor(private regionService: RegionApiService,
              private dialog: MatDialog) {
  }

  loadRegions(){
    this.regionService.getAllRegions().subscribe(response => this.regions = response)
  }

  openAddModal(){
    const dialogRef = this.dialog.open(AddRegionModalComponent, {
      width: '40%',
      panelClass: 'custom-mat-dialog-container',
    });

    dialogRef.afterClosed().subscribe(result => {
      this.loadRegions()

    });

  }

  openModifyModal(region: Region){
    const dialogRef = this.dialog.open(ModifyRegionModalComponent, {
      width: '40%',
      panelClass: 'custom-mat-dialog-container',
      data: region
    });

    dialogRef.afterClosed().subscribe(result => {
      this.loadRegions()
    });
  }

  openDeleteModal(region: Region){
    const dialogRef = this.dialog.open(DeleteRegionModalComponent, {
      width: '40%',
      panelClass: 'custom-mat-dialog-container',
      data: region
    });

    dialogRef.afterClosed().subscribe(result => {
      this.regionService.deleteRegion(region.regionId.toString())
      this.loadRegions()

    });
  }
}
