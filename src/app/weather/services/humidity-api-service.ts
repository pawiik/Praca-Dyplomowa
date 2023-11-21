import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {AuthService} from "./auth-service";
import {Observable} from "rxjs";
import {Humidity} from "../../shared/model/Humidity";

@Injectable({
  providedIn: 'root'
})
export class HumidityApiService {

  private apiUrl: string = 'http://localhost:8080/humidity'

  constructor(private httpClient: HttpClient, private authService: AuthService) {
  }

  public getAllHumidity(): Observable<Humidity[]> {
    let jwtToken: string = this.authService.authData.jwtToken
    let options: {} = {
      headers: new HttpHeaders({
        "Authorization": jwtToken
      })
    }
    return this.httpClient.get<Humidity[]>(this.apiUrl + "/falls", options);
  }

  public addNewHumidity(body: {}): Observable<Humidity> {
    let jwtToken: string = this.authService.authData.jwtToken
    let options: {} = {
      headers: new HttpHeaders({
        "Authorization": jwtToken
      })
    }
    return this.httpClient.post<Humidity>(this.apiUrl + "/", options, body)
  }
}
