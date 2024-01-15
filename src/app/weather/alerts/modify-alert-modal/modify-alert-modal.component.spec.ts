import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModifyAlertModalComponent } from './modify-alert-modal.component';

describe('ModifyAlertModalComponent', () => {
  let component: ModifyAlertModalComponent;
  let fixture: ComponentFixture<ModifyAlertModalComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ModifyAlertModalComponent]
    });
    fixture = TestBed.createComponent(ModifyAlertModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
