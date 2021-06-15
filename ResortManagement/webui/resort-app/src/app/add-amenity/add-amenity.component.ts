import { Amenities } from './../amenity-details/amenities';
import { Employee } from './../employeedash/employee';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AmenityService } from '../amenity.service';
import { EmployeeService } from '../employee.service';

@Component({
  selector: 'app-add-amenity',
  templateUrl: './add-amenity.component.html',
  styleUrls: ['./add-amenity.component.css']
})
export class AddAmenityComponent implements OnInit {
  id: number;
  unitprice: number;
  cat: string;
  name: string;
  e: Employee = new Employee();
  am: Amenities;

  constructor(private router: Router, private route: ActivatedRoute,
              private employeeService: EmployeeService, private amService: AmenityService) { }

  ngOnInit(): void {
    this.loadData();
  }

  loadData() {
    // tslint:disable-next-line: radix
    this.id = parseInt(this.route.snapshot.paramMap.get('id'));

    // tslint:disable-next-line: deprecation
    this.employeeService.getEmployeeById(this.id).subscribe(data => {
      this.e = data;
    }, error => console.log(error));
  }

  loadHome() {
    // tslint:disable-next-line: radix
    this.id = parseInt(this.route.snapshot.paramMap.get('id'));
    this.router.navigate(['empDash/', this.id]);
  }

  addService() {
    this.am = new Amenities();

    // tslint:disable-next-line: radix
    this.id = parseInt(this.route.snapshot.paramMap.get('id'));

    if (this.name === undefined || this.unitprice === undefined || this.cat === undefined) {
      alert('All fields are required. Please fill all the fields');
      this.loadData();
    } else {
      const re = /\ /gi;

      this.am.amenityName = this.name.replace(re, '_');
      this.am.price = this.unitprice;
      this.am.employeeId = this.id;
      this.am.amenityCategory = this.cat;

      // tslint:disable-next-line: deprecation
      this.amService.addNewAmentiy(this.am).subscribe(data => {
        console.log(data);
        alert(data);
        this.loadHome();
      }, error => console.log(error));
    }
  }

  showDetails() {
    // tslint:disable-next-line: radix
    this.id = parseInt(this.route.snapshot.paramMap.get('id'));
    this.router.navigate(['eDetails/', this.id]);
  }

  changePassword() {

  }

  addCoupon() {}

  showAllBookings() {
    // tslint:disable-next-line: radix
    this.id = parseInt(this.route.snapshot.paramMap.get('id'));
    this.router.navigate(['eHistory/', this.id]);
  }

  showCurrentPending() {
    // tslint:disable-next-line: radix
    this.id = parseInt(this.route.snapshot.paramMap.get('id'));
    this.router.navigate(['pending/', this.id]);
  }
}
