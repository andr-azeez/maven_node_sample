import { Member } from './../memberdash/member';
import { Employee } from './../employeedash/employee';
import { Amenities } from './../amenity-details/amenities';
import { Booking } from './../place-booking/booking';
import { MemberService } from './../member.service';
import { AmenityService } from './../amenity.service';
import { BookingService } from './../booking.service';
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { EmployeeService } from '../employee.service';

@Component({
  selector: 'app-purchase-details',
  templateUrl: './purchase-details.component.html',
  styleUrls: ['./purchase-details.component.css']
})
export class PurchaseDetailsComponent implements OnInit {

  id: number;
  bId: number;
  book: Booking;
  am: Amenities;
  e: Employee;
  m: Member;
  amId: number;
  memId: number;

  constructor(private router: Router, private route: ActivatedRoute,
              private bookingService: BookingService, private employeeService: EmployeeService,
              private amService: AmenityService, private memberService: MemberService) { }

  ngOnInit(): void {
    this.loadData();
  }

  loadData() {
    this.book = new Booking();
    this.e = new Employee();
    this.m = new Member();
    this.am = new Amenities();

    // tslint:disable-next-line: radix
    this.bId = parseInt(this.route.snapshot.paramMap.get('id'));

    // tslint:disable-next-line: deprecation
    this.bookingService.getBookingById(this.bId).subscribe(data => {
      this.book = data;

      this.amId = this.book.amenityId;
      this.memId = this.book.memberId;
      this.id = this.book.employeeId;

      // tslint:disable-next-line: deprecation
      this.memberService.getMemberById(this.memId).subscribe(mem => {
        console.log('Member: ', mem);
        this.m = mem;
      }, error => console.log(error));

      // tslint:disable-next-line: deprecation
      this.amService.getAmenityById(this.amId).subscribe(item => {
        console.log('Service: ', item);
        this.am = item;
      }, error => console.log(error));

      // tslint:disable-next-line: deprecation
      this.employeeService.getEmployeeById(this.id).subscribe(emp => {
        console.log('Employee: ', emp);
        this.e = emp;
      }, error => console.log(error));
    }, error => console.log(error));
  }

  addService() {
    this.router.navigate(['newAm/', this.id]);
  }

  showDetails() {
    this.router.navigate(['eDetails/', this.id]);
  }

  changePassword() {

  }

  showAllBookings() {
    console.log(this.id);
    this.router.navigate(['eHistory/', this.id]);
  }

  showCurrentPending() {
    this.router.navigate(['pending/', this.id]);
  }

}
