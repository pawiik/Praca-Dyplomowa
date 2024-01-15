import { Component } from '@angular/core';
import {Alert} from "../../shared/model/Alert";
import {AlertApiService} from "../services/alert-api-service";
import {City} from "../../shared/model/City";
import {CityApiService} from "../services/city-api-service";
import { MatDialog } from '@angular/material/dialog';
import {AddAlertModalComponent} from "./add-alert-modal/add-alert-modal.component";
import {ModifyAlertModalComponent} from "./modify-alert-modal/modify-alert-modal.component";
import {DeleteAlertModalComponent} from "./delete-alert-modal/delete-alert-modal.component";


interface Disp{
  cityName: string
  danger: number
  description: string
  alertId: number
}

@Component({
  selector: 'app-alerts',
  templateUrl: './alerts.component.html',
  styleUrls: ['./alerts.component.css']
})
export class AlertsComponent {

  cities: City[] = []
  alerts: Alert[] = []
  selectedCity: string = "0"
  constructor(private alertService: AlertApiService,
              private cityService: CityApiService,
              public dialog: MatDialog) {
    this.loadCities()
    this.loadAlerts()
  }

  openAddModal(){
    const dialogRef = this.dialog.open(AddAlertModalComponent, {
      width: '40%',
      panelClass: 'custom-mat-dialog-container'
    });

    dialogRef.afterClosed().subscribe(result => {
    });
  }

  openModifyModal(){
    const selectedItems = this.alerts.filter(item => item.selected);
    if (selectedItems.length > 0) {
      let firstElement = selectedItems[0]
      const dialogRef = this.dialog.open(ModifyAlertModalComponent, {
        width: '40%',
        data: firstElement
      });

      dialogRef.afterClosed().subscribe(result => {
        console.log(result)
      });
    } else {
    }
  }

  openDeleteModal(){
    const selectedItems = this.alerts.filter(item => item.selected);
    if (selectedItems.length > 0) {
      const dialogRef = this.dialog.open(DeleteAlertModalComponent, {
        width: '40%',
        data: { selectedItems }
      });
      dialogRef.afterClosed().subscribe(result => {
        if(result === 'delete') {
          this.deleteSelected();
        }
      });
  }}

  deleteSelected(){

  }

  getAlertsFromCity(cityId: number){
    this.alertService.getAlertForCity(this.selectedCity).subscribe(response => {
      this.alerts = []
      this.alerts.push(response)
    })
  }

  private loadCities() {
    this.cityService.getAllCities().subscribe(response => this.cities = response)
  }

  private loadAlerts(){
    this.alertService.getAllAlerts().subscribe(response => this.alerts = response)
  }


}
