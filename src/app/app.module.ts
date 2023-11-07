import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { WelcomeComponent } from './weather/welcome/welcome.component';
import { NavBarComponent } from './weather/nav-bar/nav-bar.component';
import {Routes, RouterOutlet} from "@angular/router";
import { AppRoutingModule } from './weather/app-routing.module';
import { FooterComponent } from './weather/footer/footer.component';
import {AppComponent} from "./app.component";
import { FormsModule } from '@angular/forms';
import {HttpClient, HttpClientModule} from "@angular/common/http";

@NgModule({
  declarations: [
    AppComponent,
    WelcomeComponent,
    NavBarComponent,
    FooterComponent
  ],
  imports: [
    BrowserModule,
    RouterOutlet,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
