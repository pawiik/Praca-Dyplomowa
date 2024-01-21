import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {City} from "../../shared/model/City";
import {User} from "../../shared/model/User";
import {UserInformationComponent} from "../user-information/user-information.component";
import {AuthService} from "./auth-service";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class UserApiService {
  private apiUrl = 'http://localhost:8080/user'
  constructor(private httpClient: HttpClient, private authService: AuthService){
  }

  getUserInformation(userId: string){
    let address = this.apiUrl + '/' + userId
    let jwtToken = this.authService.authData.jwtToken
    let options = {
      headers: new HttpHeaders({
        "Authorization": jwtToken,
      })
    }
    return this.httpClient.get<User>(address, options);

  }

  updateUserInformation(body: {}): Observable<User>{
    let jwtToken = this.authService.authData.jwtToken
    let options = {
      headers: new HttpHeaders({
        "Authorization": jwtToken,
      })
    }
    return this.httpClient.put<User>(this.apiUrl + '/', body, options)
  }

}
