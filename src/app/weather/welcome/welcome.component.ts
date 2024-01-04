import {Component, ComponentRef, ViewChild, ViewContainerRef} from '@angular/core';
import {ApiService} from "../services/api.service";
import {City} from "../../shared/model/City";
import {CityDetailsComponent} from "../city-details/city-details.component";

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.css']
})
export class WelcomeComponent {
  @ViewChild('container', {read: ViewContainerRef}) container!: ViewContainerRef;
  componentRef!: ComponentRef<any>

  searchTerm: string = '';
  // allCities: string[] = ['Krakow', 'Katowice', 'Warsaw', 'Wroclaw']; // Your list of cities
  // suggestions: string[] = [];
  allCities: City[] = []
  suggestions: City[] = []
  constructor(private service: ApiService) {
    this.loadCities()
  }

  loadCities() {
    this.service.getAllCities().subscribe(cities => this.allCities = cities);
  }

  getCities(){
    let data = this.service.getAllCities().subscribe(cities =>{
      this.allCities = cities
    })
    console.log(this.allCities)
  }
  getRandomColor() {
    const letters = '0123456789ABCDEF';
    let color = '#';
    for (let i = 0; i < 6; i++) {
      color += letters[Math.floor(Math.random() * 16)];
    }
    return color;
  }
  searchCities() {
    if (this.searchTerm) {
      // this.getCities()
      // this.suggestions = this.allCities.filter(city =>
      //   city.cityName.toLowerCase().startsWith(this.searchTerm.toLowerCase())
      // );
      this.suggestions = this.allCities.filter(city => {
        console.log(city.cityName);
        return city.cityName && city.cityName.toLowerCase().includes(this.searchTerm.toLowerCase());
      });
      console.log("found")
      console.log(this.suggestions)
    } else {
      console.log("didn't find")
      // this.getCities()
      this.suggestions = [];
    }
  }

  showCityInfo(cityId: number){
    console.log("hello")
    console.log(cityId)
    this.container.clear();

    this.componentRef = this.container.createComponent(CityDetailsComponent)
    this.componentRef.instance.cityId = cityId
  }

}
