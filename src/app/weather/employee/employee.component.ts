import {Component, Type, ViewChild, ViewContainerRef} from '@angular/core';
import {Subscription} from "rxjs";
import {DynamicComponentAnchorDirectiveEmployee} from "./employee-dynamic-load";
import {EmployeeSharedService} from "./employee-shared-service";
import {WelcomeComponent} from "../welcome/welcome.component";

@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css']
})
export class EmployeeComponent {
  // @ViewChild(DynamicComponentAnchorDirectiveEmployee, { static: true }) dynamicComponentAnchor!: DynamicComponentAnchorDirectiveEmployee;
  // private componentSubscribtion: Subscription
  // constructor(private sharedService: EmployeeSharedService,
  //             private viewContainerRef: ViewContainerRef
  // ) {
  //   this.componentSubscribtion = this.sharedService.componentToLoad.subscribe(component => this.loadDynamicComponent(component))
  // }
  //
  // ngOnInit(){
  //   this.loadDynamicComponent(WelcomeComponent)
  // }
  //
  // loadDynamicComponent(component: Type<any>) {
  //   const viewContainerRef = this.dynamicComponentAnchor.viewContainerRef;
  //   viewContainerRef.clear();
  //   const componentRef = viewContainerRef.createComponent(component);
  // }
  //
  // ngOnDestroy(): void {
  //   if (this.componentSubscribtion) {
  //     this.componentSubscribtion.unsubscribe();
  //   }
  // }
}
