import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ActualTemperatureComponent } from './actual-temperature.component';

describe('ActualTemperatureComponent', () => {
  let component: ActualTemperatureComponent;
  let fixture: ComponentFixture<ActualTemperatureComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ActualTemperatureComponent]
    });
    fixture = TestBed.createComponent(ActualTemperatureComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
