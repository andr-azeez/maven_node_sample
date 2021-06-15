import { EmployeeService } from './../employee.service';
import { Employee } from './../employeedash/employee';
import { Amenities } from './amenities';
import { AmenityService } from './../amenity.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-amenity-details',
  templateUrl: './amenity-details.component.html',
  styleUrls: ['./amenity-details.component.css']
})
export class AmenityDetailsComponent implements OnInit {

  val1: number;
  id: number;
  am: Amenities = new Amenities();
  e: Employee = new Employee();

  constructor(private router: Router, private route: ActivatedRoute,
              private amService: AmenityService, private empService: EmployeeService) { }

  ngOnInit(): void {
    this.loadData();
  }

  loadData() {
    // tslint:disable-next-line: radix
    this.id = parseInt(this.route.snapshot.paramMap.get('id'));

    // tslint:disable-next-line: deprecation
    this.amService.getAmenityById(this.id).subscribe(data => {
      console.log(data);
      this.am = data;

      // tslint:disable-next-line: deprecation
      this.empService.getEmployeeById(this.am.employeeId).subscribe(info => {
        console.log(info);
        this.e = info;
      }, error => console.log(error));
    }, error => console.log(error));
  }

  showAllBookings() {
    // tslint:disable-next-line: radix
    this.val1 = parseInt(this.route.snapshot.paramMap.get('mId'));
    this.router.navigate(['history/', this.val1]);
  }

  showDetails() {
    // tslint:disable-next-line: radix
    this.val1 = parseInt(this.route.snapshot.paramMap.get('id'));
    this.router.navigate(['details/', this.val1]);
  }

  changePassword() {
    // tslint:disable-next-line: radix
    this.val1 = parseInt(this.route.snapshot.paramMap.get('id'));
    this.router.navigate(['pass/', this.val1]);
  }

  showCurrentBookings() {
    // tslint:disable-next-line: radix
    this.val1 = parseInt(this.route.snapshot.paramMap.get('id'));
    this.router.navigate(['current/', this.val1]);
  }

  showFeedback() {
    // tslint:disable-next-line: radix
    this.val1 = parseInt(this.route.snapshot.paramMap.get('id'));
    this.router.navigate(['contact/', this.val1]);
  }

  loadHome() {
    // tslint:disable-next-line: radix
    this.val1 = parseInt(this.route.snapshot.paramMap.get('id'));
    this.router.navigate(['home/', this.val1]);
  }

  bookService() {
    // tslint:disable-next-line: radix
    this.val1 = parseInt(this.route.snapshot.paramMap.get('id'));
    this.router.navigate(['memDash/', 'ALL', this.val1]);
  }
}
