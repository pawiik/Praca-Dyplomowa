import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {AuthService} from "./auth-service";
import {Observable} from "rxjs";
import {Humidity} from "../../shared/model/Humidity";
import {MeasurementStation} from "../../shared/model/MeasurementStation";
import {City} from "../../shared/model/City";

@Injectable({
  providedIn: 'root'
})
export class MeasurementStationApiService {

  private apiUrl: string = 'http://localhost:8080/measurementStation'

  constructor(private httpClient: HttpClient, private authService: AuthService) {
  }

  public getAllMeasurementStations(): Observable<MeasurementStation[]> {
    let jwtToken: string = this.authService.authData.jwtToken
    let options: {} = {
      headers: new HttpHeaders({
        "Authorization": jwtToken
      })
    }
    return this.httpClient.get<MeasurementStation[]>(this.apiUrl + "/stations", options);
  }

  public addNewMeasurementStation(body: {}): Observable<MeasurementStation> {
    let jwtToken: string = this.authService.authData.jwtToken
    let options: {} = {
      headers: new HttpHeaders({
        "Authorization": jwtToken
      })
    }
    return this.httpClient.post<MeasurementStation>(this.apiUrl + "/", body, options)
  }

  public getStationById(body: {}): Observable<MeasurementStation>{
    let jwtToken: string = this.authService.authData.jwtToken
    let options: {} = {
      headers: new HttpHeaders({
        "Authorization": jwtToken
      })
    }
    return this.httpClient.get<MeasurementStation>(this.apiUrl + "/stations", options);
  }

  public modifyMeasurementStation(body:{}){
    let jwtToken: string = this.authService.authData.jwtToken
    let options: {} = {
      headers: new HttpHeaders({
        "Authorization": jwtToken,
      })
    }

    return this.httpClient.put<City>(this.apiUrl + "/", body, options)
  }

  public deleteMeasurementStation(stationId: string){
    let jwtToken: string = this.authService.authData.jwtToken
    let options: {} = {
      headers: new HttpHeaders({
        "Authorization": jwtToken,
      })
    }

    return this.httpClient.delete<City>( `${this.apiUrl}/?stationId=${stationId}`, options)
  }

}
