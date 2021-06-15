import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MemberBookingsComponent } from './member-bookings.component';

describe('MemberBookingsComponent', () => {
  let component: MemberBookingsComponent;
  let fixture: ComponentFixture<MemberBookingsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MemberBookingsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MemberBookingsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
