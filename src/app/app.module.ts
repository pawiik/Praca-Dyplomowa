import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { WelcomeComponent } from './weather/welcome/welcome.component';
import { NavBarComponent } from './weather/nav-bar/nav-bar.component';
import {Routes, RouterOutlet} from "@angular/router";
import { AppRoutingModule } from './weather/app-routing.module';
import { FooterComponent } from './weather/footer/footer.component';
import {AppComponent} from "./app.component";
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpClient, HttpClientModule} from "@angular/common/http";
import { LoginComponent } from './weather/login/login.component';
import { RegisterComponent } from './weather/register/register.component';
import { UserInformationComponent } from './weather/user-information/user-information.component';
import { MeasurementsTableComponent } from './weather/measurements-table/measurements-table.component';
import { EmployeeComponent } from './weather/employee/employee.component';
import { CityDetailsComponent } from './weather/city-details/city-details.component';
import { ActualTemperatureComponent } from './weather/city-details/actual-temperature/actual-temperature.component';
import { CityAlertsComponent } from './weather/city-details/city-alerts/city-alerts.component';
import { TemperatureByHourComponent } from './weather/city-details/temperature-by-hour/temperature-by-hour.component';
import { AlertsComponent } from './weather/alerts/alerts.component';
import { AddAlertModalComponent } from './weather/alerts/add-alert-modal/add-alert-modal.component';
import { ModifyAlertModalComponent } from './weather/alerts/modify-alert-modal/modify-alert-modal.component';
import { DeleteAlertModalComponent } from './weather/alerts/delete-alert-modal/delete-alert-modal.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatDialog, MatDialogModule} from "@angular/material/dialog";
import {MatButtonModule} from "@angular/material/button";
import moment from "moment";
import { UserAlertsComponent } from './weather/user-information/user-alerts/user-alerts.component';
import {DynamicComponentAnchorDirectiveEmployee} from "./weather/employee/employee-dynamic-load";
import { EmployeeNavBarComponent } from './weather/nav-bar/employee-nav-bar/employee-nav-bar.component';
import { UserNavBarComponent } from './weather/nav-bar/user-nav-bar/user-nav-bar.component';
import {DetailsComponent} from "./weather/welcome/details/details.component";
import { RegionsComponent } from './weather/employee/regions/regions.component';
import { CitiesComponent } from './weather/employee/cities/cities.component';
import { MeasurementStationsComponent } from './weather/employee/measurement-stations/measurement-stations.component';
import { AddCityModalComponent } from './weather/employee/cities/add-city-modal/add-city-modal.component';
import { AddMeasurementStationModalComponent } from './weather/employee/measurement-stations/add-measurement-station-modal/add-measurement-station-modal.component';
import { AddRegionModalComponent } from './weather/employee/regions/add-region-modal/add-region-modal.component';
import { AddMeasurementComponent } from './weather/employee/add-measurement/add-measurement.component';

@NgModule({
  declarations: [
    AppComponent,
    WelcomeComponent,
    NavBarComponent,
    FooterComponent,
    LoginComponent,
    RegisterComponent,
    UserInformationComponent,
    MeasurementsTableComponent,
    EmployeeComponent,
    CityDetailsComponent,
    ActualTemperatureComponent,
    CityAlertsComponent,
    TemperatureByHourComponent,
    AlertsComponent,
    AddAlertModalComponent,
    ModifyAlertModalComponent,
    DeleteAlertModalComponent,
    UserAlertsComponent,
    DynamicComponentAnchorDirectiveEmployee,
    EmployeeNavBarComponent,
    UserNavBarComponent,
    DetailsComponent,
    RegionsComponent,
    CitiesComponent,
    MeasurementStationsComponent,
    AddCityModalComponent,
    AddMeasurementStationModalComponent,
    AddRegionModalComponent,
    AddMeasurementComponent
  ],
  imports: [
    BrowserModule,
    RouterOutlet,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatDialogModule,
    MatButtonModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
