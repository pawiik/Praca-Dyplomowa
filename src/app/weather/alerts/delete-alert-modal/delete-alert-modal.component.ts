import { Component, Inject } from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {Alert} from "../../../shared/model/Alert";
import {AddAlertModalComponent} from "../add-alert-modal/add-alert-modal.component";
import {AlertApiService} from "../../services/alert-api-service";

@Component({
  selector: 'app-delete-alert-modal',
  templateUrl: './delete-alert-modal.component.html',
  styleUrls: ['./delete-alert-modal.component.css']
})
export class DeleteAlertModalComponent {

  alertId: string = ""

  constructor(@Inject(MAT_DIALOG_DATA) public data: Alert,
              public dialogRef: MatDialogRef<AddAlertModalComponent>,
              private alertService: AlertApiService) {
    console.log(data)
    this.alertId = data.alertId.toString()

  }

  deleteAlert(){
    this.alertService.deleteAlert(this.alertId).subscribe()

  }


}
