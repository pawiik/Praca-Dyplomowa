import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {City}  from "src/app/shared/model/City";

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private apiUrl = 'http://localhost:8080/city';

  constructor(private http: HttpClient) {}

  getCity(cityId: number): Observable<City> {
    return this.http.get<City>(`${this.apiUrl}/cities/${cityId}`);
  }

  getAllCities(): Observable<City[]>{
    return this.http.get<City[]>(this.apiUrl + "/cities");
  }
}
