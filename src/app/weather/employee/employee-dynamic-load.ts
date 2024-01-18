import {Directive, ViewContainerRef} from "@angular/core";

@Directive({
  selector: '[dynamic-component-anchor-employee]'
})
export class DynamicComponentAnchorDirectiveEmployee {
  constructor(public viewContainerRef: ViewContainerRef) { }
}
