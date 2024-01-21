import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeleteCityModalComponent } from './delete-city-modal.component';

describe('DeleteCityModalComponent', () => {
  let component: DeleteCityModalComponent;
  let fixture: ComponentFixture<DeleteCityModalComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DeleteCityModalComponent]
    });
    fixture = TestBed.createComponent(DeleteCityModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
