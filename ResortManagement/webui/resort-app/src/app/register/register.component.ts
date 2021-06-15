import { Employee } from './../employeedash/employee';
import { EmployeeService } from './../employee.service';
import { Member } from './../memberdash/member';
import { MemberService } from './../member.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  user = '';
  passcode = '';
  passAgain = '';

  hidden = false;

  userName = '';
  ph: string;
  em: string;
  birthDate: string;

  re = / /g;

  member: Member = new Member();
  emp: Employee = new Employee();
  errorMessage: string;

  constructor(private router: Router, private memberService: MemberService,
              private employeeService: EmployeeService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.user = this.route.snapshot.paramMap.get('val');
  }

  registerUser() {
    this.errorMessage = '';
    this.user = this.route.snapshot.paramMap.get('val');
    if (this.user === 'mem' && this.passcode === this.passAgain) {
      // replaces space with _
      this.userName = this.userName.replace(this.re, '_');

      this.member.memberName = this.userName;
      this.member.email = this.em;
      this.member.phone = this.ph;
      this.member.dateOfBirth = this.birthDate;
      this.member.passKey = this.passcode;

      // // tslint:disable-next-line: deprecation
      // this.memberService.getMemberByEmail(this.em).subscribe(info => {
      //   console.log(info);
      //   this.member = info;
      // }, error => console.log(error));

      // tslint:disable-next-line: deprecation
      this.memberService.registerMember(this.member).subscribe(data => {
        console.log(data);
        // console.log('status: ' + data.status);
        if (data === 'Member Registered Successfully! Please login to continue') {
          alert('Registration Successful! Please login to continue!');
          // this.hidden = true;
          this.router.navigate(['login/', 'new']);
        } else {
          alert(data);
          this.router.navigate(['register/', 'mem']);
        }

      }, error => console.log(error));
    }

    if (this.user === 'emp' && this.passcode === this.passAgain) {
      this.userName = this.userName.replace(this.re, '_');
      this.emp.employeeName = this.userName;
      this.emp.email = this.em;
      this.emp.phone = this.ph;
      this.emp.passKey = this.passcode;

      // tslint:disable-next-line: deprecation
      this.employeeService.registerEmployee(this.emp).subscribe(data => {
        console.log(data);

        if (data === 'Registration Successful') {
          alert(data + '! Please Login to Continue!');
          this.router.navigate(['signin']);
        } else {
          alert(data);
          this.router.navigate(['register/', 'emp']);
        }
      }, (error) => {
        console.error('an error occured');
        this.errorMessage = error;
        console.log(error);
        alert('An error occured (Try with a different email).');
        this.router.navigate(['register/', 'emp']);
      });
    }
  }

  loadMain(): void {
    this.router.navigate(['main']);
  }
}
