import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {City} from "../../shared/model/City";
import {User} from "../../shared/model/User";
import {UserInformationComponent} from "../user-information/user-information.component";
import {AuthService} from "./auth";

@Injectable({
  providedIn: 'root'
})
export class UserService{
  private apiUrl = 'http://localhost:8080/user'
  constructor(private httpClient: HttpClient, private authService: AuthService){
  }

  getUserInformation(userId: string){
    let address = this.apiUrl + '/' + userId
    console.log(address)
    return this.httpClient.get<User>(address);

  }

  updateUserInformation(body: {}){
    let jwtToken = this.authService.authData.jwtToken
    let options = {
      headers: new HttpHeaders({
        "Authorization": jwtToken,
      })
    }
    return this.httpClient.put(this.apiUrl, body, options)
  }

}
