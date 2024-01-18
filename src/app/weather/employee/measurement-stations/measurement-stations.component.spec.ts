import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MeasurementStationsComponent } from './measurement-stations.component';

describe('MeasurementStationsComponent', () => {
  let component: MeasurementStationsComponent;
  let fixture: ComponentFixture<MeasurementStationsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MeasurementStationsComponent]
    });
    fixture = TestBed.createComponent(MeasurementStationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
