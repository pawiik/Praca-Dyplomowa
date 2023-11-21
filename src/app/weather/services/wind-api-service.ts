import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {AuthService} from "./auth-service";
import {Observable} from "rxjs";
import {Wind} from "../../shared/model/Wind";

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
    return this.httpClient.post<Wind>(this.apiUrl + "/", options, body)
  }
}
