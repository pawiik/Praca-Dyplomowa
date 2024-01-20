import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModifyCityModalComponent } from './modify-city-modal.component';

describe('ModifyCityModalComponent', () => {
  let component: ModifyCityModalComponent;
  let fixture: ComponentFixture<ModifyCityModalComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ModifyCityModalComponent]
    });
    fixture = TestBed.createComponent(ModifyCityModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
