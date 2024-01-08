import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {AuthService} from "./auth-service";
import {Observable} from "rxjs";
import {Temperature} from "../../shared/model/Temperature";
import {Fall} from "../../shared/model/Fall";
import {Humidity} from "../../shared/model/Humidity";

@Injectable({
  providedIn: 'root'
})
export class TemperatureApiService {

  private apiUrl: string = 'http://localhost:8080/temperature'

  constructor(private httpClient: HttpClient, private authService: AuthService) {
  }

  public getAllTemperatures(): Observable<Temperature[]> {
    let jwtToken: string = this.authService.authData.jwtToken
    let options: {} = {
      headers: new HttpHeaders({
        "Authorization": jwtToken
      })
    }
    return this.httpClient.get<Temperature[]>(this.apiUrl + "/temperatures", options);
  }

  public addNewTemperature(body: {}): Observable<Temperature> {
    let jwtToken: string = this.authService.authData.jwtToken
    let options: {} = {
      headers: new HttpHeaders({
        "Authorization": jwtToken
      })
    }
    return this.httpClient.post<Temperature>(this.apiUrl + "/", options, body)
  }

  public loadByTimePeriod(body: {}): Observable<Temperature[]>{
    let jwtToken: string = this.authService.authData.jwtToken
    let options: {} = {
      headers: new HttpHeaders({
        "Authorization": jwtToken
      })
    }
    return this.httpClient.post<Temperature[]>(this.apiUrl + "/time", options, body)
  }

  getDayData(date: string, cityId: string): Observable<Map<number, number>> {
    let jwtToken: string = this.authService.authData.jwtToken
    let options: {} = {
      headers: new HttpHeaders({
        "Authorization": jwtToken
      })
    }
    return this.httpClient.get<Map<number, number>>(`${this.apiUrl}/day?date=${date}&cityId=${cityId}`);
  }

  getLast(cityId: string):Observable<Temperature>{
    let jwtToken: string = this.authService.authData.jwtToken
    let options: {} = {
      headers: new HttpHeaders({
        "Authorization": jwtToken
      })
    }
    return this.httpClient.get<Temperature>(`${this.apiUrl}/last?cityId=${cityId}`);
  }
}
