import { Employee } from './../employeedash/employee';
import { EmployeeService } from './../employee.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-employee-details',
  templateUrl: './employee-details.component.html',
  styleUrls: ['./employee-details.component.css']
})
export class EmployeeDetailsComponent implements OnInit {

  id: number;
  emp: Employee;
  constructor(private router: Router, private route: ActivatedRoute,
              private empService: EmployeeService) { }

  ngOnInit(): void {
    this.loadData();
  }

  loadData() {
    this.emp = new Employee();
    // tslint:disable-next-line: radix
    this.id = parseInt(this.route.snapshot.paramMap.get('id'));

    // tslint:disable-next-line: deprecation
    this.empService.getEmployeeById(this.id).subscribe(data => {
      console.log(data);
      this.emp = data;
    }, error => console.log(error));
  }

  addService() {
    // tslint:disable-next-line: radix
    this.id = parseInt(this.route.snapshot.paramMap.get('id'));
    this.router.navigate(['newAm/', this.id]);
  }

  changePassword() {

  }

  updatePhone(ph: string) {
    // tslint:disable-next-line: radix
    this.id = parseInt(this.route.snapshot.paramMap.get('id'));

    // tslint:disable-next-line: deprecation
    this.empService.getEmployeeById(this.id).subscribe(data => {
      console.log(data);
      this.emp = data;

      // tslint:disable-next-line: deprecation
      this.empService.updatePhone(this.emp, ph).subscribe(info => {
        console.log(info);
        alert(info);
        this.loadData();
      }, error => console.log(error));
    }, error => console.log(error));
  }

  showAllBookings() {
    // tslint:disable-next-line: radix
    this.id = parseInt(this.route.snapshot.paramMap.get('id'));
    this.router.navigate(['eHistory/', this.id]);
  }

  loadHome() {
    // tslint:disable-next-line: radix
    this.id = parseInt(this.route.snapshot.paramMap.get('id'));
    this.router.navigate(['empDash/', this.id]);
  }
}
