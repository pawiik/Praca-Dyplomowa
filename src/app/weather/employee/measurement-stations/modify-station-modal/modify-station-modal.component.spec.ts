import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModifyStationModalComponent } from './modify-station-modal.component';

describe('ModifyStationModalComponent', () => {
  let component: ModifyStationModalComponent;
  let fixture: ComponentFixture<ModifyStationModalComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ModifyStationModalComponent]
    });
    fixture = TestBed.createComponent(ModifyStationModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
