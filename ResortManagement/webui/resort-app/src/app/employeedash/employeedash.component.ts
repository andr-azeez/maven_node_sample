import { Observable } from 'rxjs';
import { AmenityService } from './../amenity.service';
import { EmployeeService } from './../employee.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { Amenities } from '../amenity-details/amenities';
import { Employee } from './employee';

@Component({
  selector: 'app-employeedash',
  templateUrl: './employeedash.component.html',
  styleUrls: ['./employeedash.component.css']
})
export class EmployeedashComponent implements OnInit {

  id: number;
  amList: Observable<Amenities[]>;
  e: Employee = new Employee();
  am: Amenities = new Amenities();

  constructor(private router: Router, private route: ActivatedRoute,
              private employeeService: EmployeeService, private amService: AmenityService) { }

  ngOnInit(): void {
    this.loadData();
  }

  loadData() {
    // tslint:disable-next-line: radix
    this.id = parseInt(this.route.snapshot.paramMap.get('id'));

    // tslint:disable-next-line: deprecation
    this.amService.getListByEmployee(this.id).subscribe(data => {
      console.log(data);
      this.amList = data;
    }, error => console.log(error));

    // tslint:disable-next-line: deprecation
    this.employeeService.getEmployeeById(this.id).subscribe(info => {
      console.log(info);
      this.e = info;
    }, error => console.log(error));
  }

  editPrice(amId: number, p: number) {
    // tslint:disable-next-line: deprecation
    this.amService.getAmenityById(amId).subscribe(data => {
      console.log(data);
      this.am = data;

      // tslint:disable-next-line: deprecation
      this.amService.updatePrice(this.am, p).subscribe(info => {
        console.log(info);
        if (info === 'Price Updated Successfully') {
          alert(info + '!');
          this.loadData();
        }
      }, error => console.log(error));
    }, error => console.log(error));
  }

  addService() {
    // tslint:disable-next-line: radix
    this.id = parseInt(this.route.snapshot.paramMap.get('id'));
    this.router.navigate(['newAm/', this.id]);
  }

  showDetails() {
    // tslint:disable-next-line: radix
    this.id = parseInt(this.route.snapshot.paramMap.get('id'));
    this.router.navigate(['eDetails/', this.id]);
  }

  changePassword() {

  }

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
