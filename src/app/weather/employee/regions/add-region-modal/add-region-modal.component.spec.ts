import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddRegionModalComponent } from './add-region-modal.component';

describe('AddRegionModalComponent', () => {
  let component: AddRegionModalComponent;
  let fixture: ComponentFixture<AddRegionModalComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AddRegionModalComponent]
    });
    fixture = TestBed.createComponent(AddRegionModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
