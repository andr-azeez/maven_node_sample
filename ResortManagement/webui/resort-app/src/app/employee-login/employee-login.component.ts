import { Employee } from './../employeedash/employee';
import { EmployeeService } from './../employee.service';
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-employee-login',
  templateUrl: './employee-login.component.html',
  styleUrls: ['./employee-login.component.css']
})
export class EmployeeLoginComponent implements OnInit {

  userName: string;
  passcode: string;

  emp: Employee = new Employee();

  constructor(private router: Router, private employeeService: EmployeeService) { }

  ngOnInit(): void {
  }

  loadRegister() {
    this.router.navigate(['register/', 'emp']);
  }

  login() {
    // tslint:disable-next-line: deprecation
    this.employeeService.getEmployeeByEmail(this.userName).subscribe(data => {
      console.log(data);

      this.emp = data;

      if (this.emp.email === this.userName && this.emp.passKey === this.passcode) {
        this.router.navigate(['empDash/', this.emp.employeeId]);
      }
    });
  }

  loadMain() {
    this.router.navigate(['main']);
  }
}
