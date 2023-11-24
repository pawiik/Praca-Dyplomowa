import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {AuthService} from "./auth-service";
import {Observable} from "rxjs";
import {UV} from "../../shared/model/UV";
import {Fall} from "../../shared/model/Fall";
import {Humidity} from "../../shared/model/Humidity";

@Injectable({
  providedIn: 'root'
})
export class UvApiService {

  private apiUrl: string = 'http://localhost:8080/uv'

  constructor(private httpClient: HttpClient, private authService: AuthService) {
  }

  public getAllUV(): Observable<UV[]> {
    let jwtToken: string = this.authService.authData.jwtToken
    let options: {} = {
      headers: new HttpHeaders({
        "Authorization": jwtToken
      })
    }
    return this.httpClient.get<UV[]>(this.apiUrl + "/uvs", options);
  }

  public addNewUV(body: {}): Observable<UV> {
    let jwtToken: string = this.authService.authData.jwtToken
    let options: {} = {
      headers: new HttpHeaders({
        "Authorization": jwtToken
      })
    }
    return this.httpClient.post<UV>(this.apiUrl + "/", options, body)
  }

  public loadByTimePeriod(body: {}): Observable<UV[]>{
    let jwtToken: string = this.authService.authData.jwtToken
    let options: {} = {
      headers: new HttpHeaders({
        "Authorization": jwtToken
      })
    }
    return this.httpClient.post<UV[]>(this.apiUrl + "/time", options, body)
  }
}
