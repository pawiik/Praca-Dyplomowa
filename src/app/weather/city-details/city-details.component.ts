import {Component, ComponentRef, Injectable, Input, ViewChild, ViewContainerRef} from '@angular/core';
import {CityApiService} from "../services/city-api-service";
import {City} from "../../shared/model/City";
import {ActualTemperatureComponent} from "./actual-temperature/actual-temperature.component";
import {CityAlertsComponent} from "./city-alerts/city-alerts.component";
import {TemperatureByHourComponent} from "./temperature-by-hour/temperature-by-hour.component";


@Injectable({ providedIn: 'root' })
export class DataService {
  private _data: any;

  setData(value: any) {
    this._data = value;
  }

  getData(): any {
    return this._data;
  }
}


@Component({
  selector: 'app-city-details',
  templateUrl: './city-details.component.html',
  styleUrls: ['./city-details.component.css'],
})
export class CityDetailsComponent {
  @ViewChild('section1', {read: ViewContainerRef}) section1!: ViewContainerRef;
  @ViewChild('section2', {read: ViewContainerRef}) section2!: ViewContainerRef;
  @ViewChild('section3', {read: ViewContainerRef}) section3!: ViewContainerRef;
  componentRef1!: ComponentRef<any>
  componentRef2!: ComponentRef<any>
  componentRef3!: ComponentRef<any>

  cityId: number;

  constructor(private dataService: DataService,
              private cityService: CityApiService) {
    this.cityId = this.dataService.getData();
    console.log("aa")
    this.getCityData()
  }


  selectedCity!: City;

  getCityData(){
    this.cityService.getCityById(this.cityId).subscribe(response =>
    {
      this.selectedCity = response
      console.log(response)
    })
    console.log(this.selectedCity)
  }

  scrollTo(element: any): void {
    element.scrollIntoView({behavior: 'smooth', block: 'start', inline: 'nearest'});
  }

  ngAfterViewInit() {
    this.generateComponents();
    setTimeout(() => {
      const element = this.section1.element.nativeElement;
      const specificElement = element.querySelector('.section1'); // Modify as needed
      if (specificElement) {
        specificElement.scrollIntoView({ behavior: 'smooth', block: 'start' });
      }
    });
  }

  generateComponents(){

    console.log("generating temp")
    this.componentRef1 = this.section1.createComponent(ActualTemperatureComponent)

    console.log("generating alerts")
    this.componentRef2 = this.section2.createComponent(CityAlertsComponent)

    console.log("generrating hourly")
    this.componentRef3 = this.section3.createComponent(TemperatureByHourComponent)
  }
}
