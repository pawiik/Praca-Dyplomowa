import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {City} from "../../shared/model/City";
import {User} from "../../shared/model/User";
import {UserInformationComponent} from "../user-information/user-information.component";

@Injectable({
  providedIn: 'root'
})
export class UserService{
  private apiUrl = 'http://localhost:8080/user'
  constructor(private httpClient: HttpClient){
  }

  getUserInformation(userId: string){
    let address = this.apiUrl + '/' + userId
    console.log(address)
    return this.httpClient.get<User>(address);

  }

}
