import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddAlertModalComponent } from './add-alert-modal.component';

describe('AddAlertModalComponent', () => {
  let component: AddAlertModalComponent;
  let fixture: ComponentFixture<AddAlertModalComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AddAlertModalComponent]
    });
    fixture = TestBed.createComponent(AddAlertModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
