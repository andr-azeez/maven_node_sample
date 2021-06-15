import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EmployeeBookingsComponent } from './employee-bookings.component';

describe('EmployeeBookingsComponent', () => {
  let component: EmployeeBookingsComponent;
  let fixture: ComponentFixture<EmployeeBookingsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EmployeeBookingsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EmployeeBookingsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
