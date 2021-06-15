import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PlaceBookingComponent } from './place-booking.component';

describe('PlaceBookingComponent', () => {
  let component: PlaceBookingComponent;
  let fixture: ComponentFixture<PlaceBookingComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PlaceBookingComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PlaceBookingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
