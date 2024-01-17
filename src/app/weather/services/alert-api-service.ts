import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {AuthService} from "./auth-service";
import {Observable} from "rxjs";
import {Alert} from "../../shared/model/Alert";
import {ɵFormGroupValue, ɵTypedOrUntyped} from "@angular/forms";

@Injectable({
  providedIn: 'root'
})
export class AlertApiService {
  private apiUrl = 'http://localhost:8080/alert'

  constructor(private httpClient: HttpClient, private authService: AuthService) {
  }

  public getAllAlerts(): Observable<Alert[]>{
    let jwtToken: string = this.authService.authData.jwtToken
    let options: HttpHeaders = new HttpHeaders({
      "Authorization": jwtToken
    })
    let body = {headers: options}
    return this.httpClient.get<Alert[]>(this.apiUrl + "/", body)
  }

  public addNewAlert(body: {}): Observable<Alert>{
    console.log(body)
    let jwtToken: string = this.authService.authData.jwtToken
    let options: {} = {
      headers: new HttpHeaders({
        "Authorization": jwtToken,
      })
    }

    return this.httpClient.post<Alert>(this.apiUrl + "/", body, options)
  }

  public getAlertForCity(cityId: string){
    let jwtToken: string = this.authService.authData.jwtToken
    let options: {} = {
      headers: new HttpHeaders({
        "Authorization": jwtToken,
      })
    }

    return this.httpClient.get<Alert>(`${this.apiUrl}/last?cityId=${cityId}`, options)
  }

  public getAlertsForUser(userId: string){
    let jwtToken: string = this.authService.authData.jwtToken
    let options: {} = {
      headers: new HttpHeaders({
        "Authorization": jwtToken,
      })
    }
    console.log("userId")
    console.log(userId)
    return this.httpClient.get<Alert[]>(`${this.apiUrl}/alerts?userId=${userId}`, options)
  }

  public modifyAlert(value: ɵTypedOrUntyped<any, ɵFormGroupValue<any>, any>){

  }

}
