import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {AuthService} from "./auth-service";
import {Observable} from "rxjs";
import {Humidity} from "../../shared/model/Humidity";
import {MeasurementStation} from "../../shared/model/MeasurementStation";

@Injectable({
  providedIn: 'root'
})
export class MeasurementStationApiService {

  private apiUrl: string = 'http://localhost:8080/measurementStation'

  constructor(private httpClient: HttpClient, private authService: AuthService) {
  }

  public getAllHumidity(): Observable<MeasurementStation[]> {
    let jwtToken: string = this.authService.authData.jwtToken
    let options: {} = {
      headers: new HttpHeaders({
        "Authorization": jwtToken
      })
    }
    return this.httpClient.get<MeasurementStation[]>(this.apiUrl + "/stations", options);
  }

  public addNewHumidity(body: {}): Observable<MeasurementStation> {
    let jwtToken: string = this.authService.authData.jwtToken
    let options: {} = {
      headers: new HttpHeaders({
        "Authorization": jwtToken
      })
    }
    return this.httpClient.post<MeasurementStation>(this.apiUrl + "/", options, body)
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
}
