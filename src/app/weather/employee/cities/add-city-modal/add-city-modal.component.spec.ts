import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddCityModalComponent } from './add-city-modal.component';

describe('AddCityModalComponent', () => {
  let component: AddCityModalComponent;
  let fixture: ComponentFixture<AddCityModalComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AddCityModalComponent]
    });
    fixture = TestBed.createComponent(AddCityModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
