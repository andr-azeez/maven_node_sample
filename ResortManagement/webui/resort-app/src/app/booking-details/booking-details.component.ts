import { EmployeeService } from './../employee.service';
import { AmenityService } from './../amenity.service';
import { MemberService } from './../member.service';
import { Amenities } from './../amenity-details/amenities';
import { Member } from './../memberdash/member';
import { Employee } from './../employeedash/employee';
import { Booking } from './../place-booking/booking';
import { BookingService } from './../booking.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-booking-details',
  templateUrl: './booking-details.component.html',
  styleUrls: ['./booking-details.component.css']
})
export class BookingDetailsComponent implements OnInit {

  id: number;
  book: Booking;
  e: Employee;
  m: Member;
  am: Amenities;
  amId: number;
  empId: number;
  memId: number;

  constructor(private router: Router, private route: ActivatedRoute,
              private bookingService: BookingService, private memberService: MemberService,
              private amenityService: AmenityService, private employeeService: EmployeeService) { }

  ngOnInit(): void {
    this.loadData();
  }

  loadData() {
    this.book = new Booking();
    this.e = new Employee();
    this.m = new Member();
    this.am = new Amenities();

    // tslint:disable-next-line: radix
    this.id = parseInt(this.route.snapshot.paramMap.get('id'));

    // tslint:disable-next-line: deprecation
    this.bookingService.getBookingById(this.id).subscribe(data => {
      console.log('Booking: ', data);
      this.book = data;

      this.memId = this.book.memberId;
      this.amId = this.book.amenityId;
      this.empId = this.book.employeeId;

      // tslint:disable-next-line: deprecation
      this.memberService.getMemberById(this.memId).subscribe(mem => {
        console.log('Member: ', mem);
        this.m = mem;
      }, error => console.log(error));

      // tslint:disable-next-line: deprecation
      this.amenityService.getAmenityById(this.amId).subscribe(item => {
        console.log('Service: ', item);
        this.am = item;
      }, error => console.log(error));

      // tslint:disable-next-line: deprecation
      this.employeeService.getEmployeeById(this.empId).subscribe(emp => {
        console.log('Employee: ', emp);
        this.e = emp;
      }, error => console.log(error));

    }, error => console.log(error));
  }

  changePassword() {
    // tslint:disable-next-line: radix
    this.id = parseInt(this.route.snapshot.paramMap.get('id'));
    this.router.navigate(['pass/', this.id]);
  }

  showCurrentBookings() {
    // tslint:disable-next-line: radix
    this.id = parseInt(this.route.snapshot.paramMap.get('id'));
    this.router.navigate(['current/', this.id]);
  }

  showDetails() {
    // tslint:disable-next-line: radix
    this.id = parseInt(this.route.snapshot.paramMap.get('id'));
    this.router.navigate(['details/', this.id]);
  }

  showFeedback() {
    // tslint:disable-next-line: radix
    this.id = parseInt(this.route.snapshot.paramMap.get('id'));
    this.router.navigate(['contact/', this.id]);
  }

  goToHome() {
    // tslint:disable-next-line: radix
    this.id = parseInt(this.route.snapshot.paramMap.get('id'));
    this.router.navigate(['home/', this.id]);
  }

  showAllBookings() {
    // tslint:disable-next-line: radix
    this.id = parseInt(this.route.snapshot.paramMap.get('id'));
    this.router.navigate(['history/', this.id]);
  }
}
