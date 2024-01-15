import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {WelcomeComponent} from "./welcome/welcome.component";
import {LoginComponent} from "./login/login.component";
import {RegisterComponent} from "./register/register.component";
import {UserInformationComponent} from "./user-information/user-information.component";
import {MeasurementsTableComponent} from "./measurements-table/measurements-table.component";
import {AlertsComponent} from "./alerts/alerts.component";

const routes: Routes = [
  { path: 'home', component: WelcomeComponent },
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'login', component: LoginComponent},
  { path: 'register', component: RegisterComponent},
  { path: 'user-information', component: UserInformationComponent},
  { path: 'data-analysis', component: MeasurementsTableComponent},
  { path: 'alerts', component: AlertsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
