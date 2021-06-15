import { FormsModule } from '@angular/forms';
import { RouterTestingModule } from '@angular/router/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { MemberService } from './../member.service';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { MemberLoginComponent } from './memberlogin.component';

describe('LoginComponent', () => {
  let component: MemberLoginComponent;
  let fixture: ComponentFixture<MemberLoginComponent>;
  let service: MemberService;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MemberLoginComponent ],
      imports: [
        FormsModule,
        HttpClientTestingModule,
        RouterTestingModule],
      providers: [MemberService]
    })
    .compileComponents();

    service = TestBed.inject(MemberService);
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MemberLoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
