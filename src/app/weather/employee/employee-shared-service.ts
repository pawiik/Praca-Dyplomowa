import {EventEmitter, Injectable, Type} from "@angular/core";

@Injectable({
  providedIn: 'root'
})
export class EmployeeSharedService {
  public componentToLoad = new EventEmitter<Type<any>>;

  constructor() { }

  loadComponent(component: Type<any>) {
    this.componentToLoad.emit(component);
    console.log("shared")
  }
}
