import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TemperatureByHourComponent } from './temperature-by-hour.component';

describe('TemperatureByHourComponent', () => {
  let component: TemperatureByHourComponent;
  let fixture: ComponentFixture<TemperatureByHourComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TemperatureByHourComponent]
    });
    fixture = TestBed.createComponent(TemperatureByHourComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
