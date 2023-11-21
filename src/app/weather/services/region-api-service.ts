import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {AuthService} from "./auth-service";
import {Observable} from "rxjs";
import {Region} from "../../shared/model/Region";

@Injectable({
  providedIn: 'root'
})
export class RegionApiService {

  private apiUrl: string = 'http://localhost:8080/region'

  constructor(private httpClient: HttpClient, private authService: AuthService) {
  }

  getRegionById(regionId: number): Observable<Region>{
    let jwtToken = this.authService.authData.jwtToken
    let options = {
      headers: new HttpHeaders({
        "Authorization": jwtToken,
      })
    }
    return this.httpClient.get<Region>(this.apiUrl + "regions/" + regionId, options)
  }
  getRegionByName(regionName: string): Observable<Region> {
    let jwtToken = this.authService.authData.jwtToken
    let options = {
      headers: new HttpHeaders({
        "Authorization": jwtToken,
      })
    }
    return this.httpClient.get<Region>(this.apiUrl + "regions/" + regionName, options)

  }

  getAllRegions(): Observable<Region[]>{
    let jwtToken = this.authService.authData.jwtToken
    let options = {
      headers: new HttpHeaders({
        "Authorization": jwtToken,
      })
    }
    return this.httpClient.get<Region[]>(this.apiUrl + "regions/name/", options)

  }

  addNewRegion(body: {}): Observable<Region>{
    let jwtToken = this.authService.authData.jwtToken
    let options = {
      headers: new HttpHeaders({
        "Authorization": jwtToken,
      })
    }
    return this.httpClient.post<Region>(this.apiUrl + "/", options, body)

  }

  updateRegionName(body: {}){
    let jwtToken = this.authService.authData.jwtToken
    let options = {
      headers: new HttpHeaders({
        "Authorization": jwtToken,
      })
    }
    return this.httpClient.put<Region>(this.apiUrl + "/", options, body)

  }

}
