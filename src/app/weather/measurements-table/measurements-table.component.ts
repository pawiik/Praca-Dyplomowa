import { Component } from '@angular/core';
import {AuthService} from "../services/auth-service";

@Component({
  selector: 'app-measurements-table',
  templateUrl: './measurements-table.component.html',
  styleUrls: ['./measurements-table.component.css']
})
export class MeasurementsTableComponent {

  constructor(authService: AuthService) {
  }

  loadFalls(){

  }
  loadWinds(){

  }

  loadTemperatures(){

  }

  loadUV(){

  }

  loadHumidity(){

  }

  tableData: [] | undefined
  onSubmit() {
  }
}
