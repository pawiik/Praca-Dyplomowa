import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddMeasurementStationModalComponent } from './add-measurement-station-modal.component';

describe('AddMeasurementStationModalComponent', () => {
  let component: AddMeasurementStationModalComponent;
  let fixture: ComponentFixture<AddMeasurementStationModalComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AddMeasurementStationModalComponent]
    });
    fixture = TestBed.createComponent(AddMeasurementStationModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
