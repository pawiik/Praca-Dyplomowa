import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {AuthService} from "./auth";

@Injectable({
  providedIn: 'root'
})
export class RegionService{

  private apiUrl: string = 'http://localhost:8080/region'

  constructor(private httpClient: HttpClient, private authService: AuthService) {
  }

  getRegionById(regionId: number){
    let jwtToken = this.authService.authData.jwtToken
    let options = {
      headers: new HttpHeaders({
        "Authorization": jwtToken,
      })
    }
    return this.httpClient.get(this.apiUrl, )
  }
  getRegionByName(){

  }

  getAllRegions(){

  }

  addNewRegion(){

  }

  updateRegionName(){

  }

}
