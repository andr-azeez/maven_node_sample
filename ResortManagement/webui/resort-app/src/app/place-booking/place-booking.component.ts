import { MemberService } from './../member.service';
import { EmployeeService } from './../employee.service';
import { Employee } from './../employeedash/employee';
import { Amenities } from './../amenity-details/amenities';
import { AmenityService } from './../amenity.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { Member } from '../memberdash/member';

@Component({
  selector: 'app-place-booking',
  templateUrl: './place-booking.component.html',
  styleUrls: ['./place-booking.component.css']
})
export class PlaceBookingComponent implements OnInit {
  id: number;
  amId: number;
  am: Amenities;
  emp: Employee;
  quantity: number;
  m: Member;

  constructor(private router: Router, private route: ActivatedRoute,
              private amenityService: AmenityService, private memberService: MemberService,
              private employeeService: EmployeeService) { }

  ngOnInit(): void {
    this.loadData();
  }

  bookService() {
    this.m = new Member();

    console.log(this.quantity);

    if (this.quantity === undefined) {
      alert('Please enter the quantity!');
      this.loadData();
    } else {
      // tslint:disable-next-line: radix
      this.id = parseInt(this.route.snapshot.paramMap.get('id'));
      // tslint:disable-next-line: radix
      this.amId = parseInt(this.route.snapshot.paramMap.get('ser'));

      // tslint:disable-next-line: deprecation
      this.memberService.getMemberById(this.id).subscribe(data => {
        console.log('Member: ', data);
        this.m = data;

        // tslint:disable-next-line: deprecation
        this.memberService.bookAmenity(this.m, this.amId, this.quantity).subscribe(info => {
          console.log(info);
          if (info === 'Amenity Booked Successfully') {
            alert(info + '! Redirecting to Home Page');
            this.router.navigate(['home/', this.id]);
          }
        }, error => console.log(error));
      }, error => console.log(error));
    }
  }

  loadData() {
    this.am = new Amenities();

    // tslint:disable-next-line: radix
    this.id = parseInt(this.route.snapshot.paramMap.get('id'));
    // tslint:disable-next-line: radix
    this.amId = parseInt(this.route.snapshot.paramMap.get('ser'));

    // tslint:disable-next-line: deprecation
    this.amenityService.getAmenityById(this.amId).subscribe(data => {
      console.log(data);
      this.am = data;

      // tslint:disable-next-line: deprecation
      this.employeeService.getEmployeeById(this.am.employeeId).subscribe(info => {
        console.log(info);
        this.emp = info;
      }, error => console.log(error));
    }, error => console.log(error));
  }

  loadHome() {
     // tslint:disable-next-line: radix
    this.id = parseInt(this.route.snapshot.paramMap.get('id'));
    this.router.navigate(['home/', this.id]);
  }

  placeOrder() {

  }

  showDetails() {
    // tslint:disable-next-line: radix
    this.id = parseInt(this.route.snapshot.paramMap.get('id'));
    this.router.navigate(['details/', this.id]);
  }

  changePassword() {
    // tslint:disable-next-line: radix
    this.id = parseInt(this.route.snapshot.paramMap.get('id'));
  }
}
