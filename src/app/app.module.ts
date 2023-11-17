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

@NgModule({
  declarations: [
    AppComponent,
    WelcomeComponent,
    NavBarComponent,
    FooterComponent,
    LoginComponent,
    RegisterComponent,
    UserInformationComponent
  ],
    imports: [
        BrowserModule,
        RouterOutlet,
        AppRoutingModule,
        FormsModule,
        HttpClientModule,
        ReactiveFormsModule
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
