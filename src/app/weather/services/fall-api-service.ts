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
    return this.httpClient.post<Fall>(this.apiUrl + "/", body, options)
  }

  public loadByTimePeriod(body: {}): Observable<Fall[]>{
    let jwtToken: string = this.authService.authData.jwtToken
    // let options: {} = {
    //   headers: new HttpHeaders({
    //     "Authorization": jwtToken
    //   })
    // }

    console.log(body)
    return this.httpClient.post<Fall[]>(this.apiUrl + "/time", body)
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

  getLast(cityId: string):Observable<Fall>{
    let jwtToken: string = this.authService.authData.jwtToken
    let options: {} = {
      headers: new HttpHeaders({
        "Authorization": jwtToken
      })
    }
    return this.httpClient.get<Fall>(`${this.apiUrl}/last?cityId=${cityId}`);
  }

}
