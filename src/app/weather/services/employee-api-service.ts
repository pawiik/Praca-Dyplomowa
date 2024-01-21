import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {AuthService} from "./auth-service";
import {Observable} from "rxjs";
import {Fall} from "../../shared/model/Fall";
import {Employee} from "../../shared/model/Employee";

@Injectable({
  providedIn: 'root'
})
export class EmployeeApiService {

  private apiUrl: string = 'http://localhost:8080/employee'

  constructor(private httpClient: HttpClient, private authService: AuthService) {
  }

  public getAllEmployees(): Observable<Employee[]> {
    let jwtToken: string = this.authService.authData.jwtToken
    let options: {} = {
      headers: new HttpHeaders({
        "Authorization": jwtToken
      })
    }
    return this.httpClient.get<Employee[]>(this.apiUrl + "/employees", options);
  }

  public getEmployeeById(employeeId: number): Observable<Employee>{
    let jwtToken: string = this.authService.authData.jwtToken
    let options: {} = {
      headers: new HttpHeaders({
        "Authorization": jwtToken
      })
    }
    return this.httpClient.get<Employee>(this.apiUrl + employeeId, options);
  }

  public addNewEmployee(body: {}): Observable<Employee> {
    let jwtToken: string = this.authService.authData.jwtToken
    let options: {} = {
      headers: new HttpHeaders({
        "Authorization": jwtToken
      })
    }
    return this.httpClient.post<Employee>(this.apiUrl + "/", body, options)
  }
}
