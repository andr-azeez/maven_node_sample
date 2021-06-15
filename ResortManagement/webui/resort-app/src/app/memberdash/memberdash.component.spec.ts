import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MemberdashComponent } from './memberdash.component';

describe('MemberdashComponent', () => {
  let component: MemberdashComponent;
  let fixture: ComponentFixture<MemberdashComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MemberdashComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MemberdashComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
