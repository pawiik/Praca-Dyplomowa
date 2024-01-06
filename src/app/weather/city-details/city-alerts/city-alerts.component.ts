import { Component } from '@angular/core';

@Component({
  selector: 'app-city-alerts',
  templateUrl: './city-alerts.component.html',
  styleUrls: ['./city-alerts.component.css']
})
export class CityAlertsComponent {
  alerts = [
    {
      date: "Jan 06, 2024",
      message: "Severe Thunderstorm Warning for your area.",
      severity: 'alert-color-1'
    },
    {
      date: "Jan 07, 2024",
      message: "Flood Alert: Heavy rains expected overnight.",
      severity: 'alert-color-2'
    },
  ];
}
