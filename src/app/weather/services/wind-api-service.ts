import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {AuthService} from "./auth-service";
import {Observable} from "rxjs";
import {Wind} from "../../shared/model/Wind";
import {Fall} from "../../shared/model/Fall";
import {Humidity} from "../../shared/model/Humidity";
import {UV} from "../../shared/model/UV";

@Injectable({
  providedIn: 'root'
})
export class WindApiService {

  private apiUrl: string = 'http://localhost:8080/wind'

  constructor(private httpClient: HttpClient, private authService: AuthService) {
  }

  public getAllWinds(): Observable<Wind[]> {
    let jwtToken: string = this.authService.authData.jwtToken
    let options: {} = {
      headers: new HttpHeaders({
        "Authorization": jwtToken
      })
    }
    return this.httpClient.get<Wind[]>(this.apiUrl + "/winds", options);
  }

  public addNewWind(body: {}): Observable<Wind> {
    let jwtToken: string = this.authService.authData.jwtToken
    let options: {} = {
      headers: new HttpHeaders({
        "Authorization": jwtToken
      })
    }
    return this.httpClient.post<Wind>(this.apiUrl + "/", body, options)
  }

  public loadByTimePeriod(body: {}): Observable<Wind[]>{
    let jwtToken: string = this.authService.authData.jwtToken
    let options: {} = {
      headers: new HttpHeaders({
        "Authorization": jwtToken
      })
    }
    return this.httpClient.post<Wind[]>(this.apiUrl + "/time", body, options)
  }

  getDayData(date: string, cityId: string): Observable<Map<number, number>> {
    let jwtToken: string = this.authService.authData.jwtToken
    let options: {} = {
      headers: new HttpHeaders({
        "Authorization": jwtToken
      })
    }
    return this.httpClient.get<Map<number, number>>(`${this.apiUrl}/day?date=${date}&cityId=${cityId}`, options);
  }

  getLast(cityId: string):Observable<Wind>{
    let jwtToken: string = this.authService.authData.jwtToken
    let options: {} = {
      headers: new HttpHeaders({
        "Authorization": jwtToken
      })
    }
    return this.httpClient.get<Wind>(`${this.apiUrl}/last?cityId=${cityId}`, options);
  }
}
