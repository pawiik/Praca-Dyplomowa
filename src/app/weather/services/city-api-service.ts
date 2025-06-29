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
    console.log("req")
    console.log(cityId)
    let jwtToken: string = this.authService.authData.jwtToken
    console.log(jwtToken)
    let options: {} = {
      headers: new HttpHeaders({
        "Authorization": jwtToken
      })
    }
    console.log(options)
    let url = `${this.apiUrl}/${cityId}`
    return this.httpClient.get<City>(url , options);
  }

  public addNewCity(body: {}): Observable<City> {
    console.log(body)
    let jwtToken: string = this.authService.authData.jwtToken
    let options: {} = {
      headers: new HttpHeaders({
        "Authorization": jwtToken,
        "Content-Type": "application/json"
      })
    }

    return this.httpClient.post<City>(this.apiUrl + "/cities", body, options)
  }

  public modifyCity(body:{}){
    console.log(body)
    let jwtToken: string = this.authService.authData.jwtToken
    let options: {} = {
      headers: new HttpHeaders({
        "Authorization": jwtToken,
      })
    }

    return this.httpClient.put<City>(this.apiUrl + "/", body, options)
  }

  public deleteCity(cityId: string){
    let jwtToken: string = this.authService.authData.jwtToken
    let options: {} = {
      headers: new HttpHeaders({
        "Authorization": jwtToken,
      })
    }

    return this.httpClient.delete<City>( `${this.apiUrl}/?cityId=${cityId}`, options)
  }
}
