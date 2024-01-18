import {Component, ComponentRef, Type, ViewChild, ViewContainerRef} from '@angular/core';
import {ApiService} from "../services/api.service";
import {City} from "../../shared/model/City";
import {CityDetailsComponent, DataService} from "../city-details/city-details.component";
import {DynamicComponentAnchorDirectiveEmployee} from "../employee/employee-dynamic-load";
import {Subscription} from "rxjs";
import {EmployeeSharedService} from "../employee/employee-shared-service";

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.css']
})
export class WelcomeComponent {
  @ViewChild(DynamicComponentAnchorDirectiveEmployee, { static: true }) dynamicComponentAnchor!: DynamicComponentAnchorDirectiveEmployee;
  private componentSubscribtion: Subscription
  constructor(private sharedService: EmployeeSharedService,
              private viewContainerRef: ViewContainerRef
  ) {
    this.componentSubscribtion = this.sharedService.componentToLoad.subscribe(component => this.loadDynamicComponent(component))
  }

  loadDynamicComponent(component: Type<any>) {
    const viewContainerRef = this.dynamicComponentAnchor.viewContainerRef;
    viewContainerRef.clear();
    const componentRef = viewContainerRef.createComponent(component);
  }

  ngOnDestroy(): void {
    if (this.componentSubscribtion) {
      this.componentSubscribtion.unsubscribe();
    }
  }


}
