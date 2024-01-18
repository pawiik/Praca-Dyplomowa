import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmployeeNavBarComponent } from './employee-nav-bar.component';

describe('EmployeeNavBarComponent', () => {
  let component: EmployeeNavBarComponent;
  let fixture: ComponentFixture<EmployeeNavBarComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EmployeeNavBarComponent]
    });
    fixture = TestBed.createComponent(EmployeeNavBarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
