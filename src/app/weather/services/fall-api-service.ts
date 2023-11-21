import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {AuthService} from "./auth";
import {Observable} from "rxjs";
import {Fall} from "../../shared/model/Fall";

@Injectable({
  providedIn: 'root'
})
export class RegionService {

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
}
