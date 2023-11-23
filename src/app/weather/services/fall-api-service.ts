import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {AuthService} from "./auth-service";
import {Observable} from "rxjs";
import {Fall} from "../../shared/model/Fall";

@Injectable({
  providedIn: 'root'
})
export class FallApiService {

  private apiUrl: string = 'http://localhost:8080/fall'

  constructor(private httpClient: HttpClient, private authService: AuthService) {
  }

  public getAllFalls(): Observable<Fall[]> {
    let jwtToken: string = this.authService.authData.jwtToken
    let options: {} = {
      headers: new HttpHeaders({
        "Authorization": jwtToken
      })
    }
    return this.httpClient.get<Fall[]>(this.apiUrl + "/falls", options);
  }

  public addNewFall(body: {}): Observable<Fall> {
    let jwtToken: string = this.authService.authData.jwtToken
    let options: {} = {
      headers: new HttpHeaders({
        "Authorization": jwtToken
      })
    }
    return this.httpClient.post<Fall>(this.apiUrl + "/", options, body)
  }

  public loadByTimePeriod(body: {}): Observable<Fall[]>{
    let jwtToken: string = this.authService.authData.jwtToken
    let options: {} = {
      headers: new HttpHeaders({
        "Authorization": jwtToken
      })
    }
    return this.httpClient.post<Fall[]>(this.apiUrl + "/time", options, body)
  }
}
