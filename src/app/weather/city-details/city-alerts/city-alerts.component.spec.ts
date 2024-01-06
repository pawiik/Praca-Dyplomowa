import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CityAlertsComponent } from './city-alerts.component';

describe('CityAlertsComponent', () => {
  let component: CityAlertsComponent;
  let fixture: ComponentFixture<CityAlertsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CityAlertsComponent]
    });
    fixture = TestBed.createComponent(CityAlertsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
