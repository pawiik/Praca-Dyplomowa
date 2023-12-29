import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {AuthService} from "./auth-service";
import {Observable} from "rxjs";
import {Fall} from "../../shared/model/Fall";
import {Employee} from "../../shared/model/Employee";
import {City} from "../../shared/model/City";

@Injectable({
  providedIn: 'root'
})
export class CityApiService {

  private apiUrl: string = 'http://localhost:8080/city'

  constructor(private httpClient: HttpClient,
              private authService: AuthService) {
  }

  public getAllCities(): Observable<City[]> {
    let jwtToken: string = this.authService.authData.jwtToken
    let options: {} = {
      headers: new HttpHeaders({
        "Authorization": jwtToken
      })
    }
    return this.httpClient.get<City[]>(this.apiUrl + "/cities", options);
  }

  public getCityById(cityId: number): Observable<City>{
    let jwtToken: string = this.authService.authData.jwtToken
    let options: {} = {
      headers: new HttpHeaders({
        "Authorization": jwtToken
      })
    }
    return this.httpClient.get<City>(this.apiUrl + cityId, options);
  }

  public addNewCity(body: {}): Observable<City> {
    let jwtToken: string = this.authService.authData.jwtToken
    let options: {} = {
      headers: new HttpHeaders({
        "Authorization": jwtToken
      })
    }
    return this.httpClient.post<City>(this.apiUrl + "/", options, body)
  }
}
