import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MeasurementsTableComponent } from './measurements-table.component';

describe('MeasurementsTableComponent', () => {
  let component: MeasurementsTableComponent;
  let fixture: ComponentFixture<MeasurementsTableComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MeasurementsTableComponent]
    });
    fixture = TestBed.createComponent(MeasurementsTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
