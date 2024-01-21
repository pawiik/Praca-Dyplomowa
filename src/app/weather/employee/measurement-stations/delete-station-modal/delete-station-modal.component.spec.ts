import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeleteStationModalComponent } from './delete-station-modal.component';

describe('DeleteStationModalComponent', () => {
  let component: DeleteStationModalComponent;
  let fixture: ComponentFixture<DeleteStationModalComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DeleteStationModalComponent]
    });
    fixture = TestBed.createComponent(DeleteStationModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
