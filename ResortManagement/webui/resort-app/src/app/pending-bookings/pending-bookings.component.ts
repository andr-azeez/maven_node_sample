import { Amenities } from './../amenity-details/amenities';
import { Employee } from './../employeedash/employee';
import { Booking } from './../place-booking/booking';
import { EmployeeService } from './../employee.service';
import { AmenityService } from './../amenity.service';
import { BookingService } from './../booking.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-pending-bookings',
  templateUrl: './pending-bookings.component.html',
  styleUrls: ['./pending-bookings.component.css']
})
export class PendingBookingsComponent implements OnInit {

  id: number;
  bookings: Booking[];
  e: Employee;
  am: Amenities;
  bDate: string;
  book: Booking;

  constructor(private router: Router, private route: ActivatedRoute,
              private bookingService: BookingService, private amService: AmenityService,
              private employeeService: EmployeeService) { }

  ngOnInit(): void {
    this.loadData();
  }

  loadData() {
    this.e = new Employee();
    this.am = new Amenities();

    // tslint:disable-next-line: radix
    this.id = parseInt(this.route.snapshot.paramMap.get('id'));

    // tslint:disable-next-line: deprecation
    this.bookingService.getPendingBookings(this.id).subscribe(data => {
      console.log(data);
      this.bookings = data;


      for (const b of this.bookings) {
        this.bDate = b.bookingDate;
        // tslint:disable-next-line: deprecation
        this.bookingService.getBookingAmount(b.bookingId).subscribe(info => {
          b.totalAmt = info;
        }, error => console.log(error));

        // tslint:disable-next-line: deprecation
        this.amService.getAmenityById(b.amenityId).subscribe(one => {
          console.log(one);
          this.am = one;
        }, error => console.log(error));
      }

      // tslint:disable-next-line: deprecation
      this.employeeService.getEmployeeById(this.id).subscribe(data1 => {
        console.log(data1);
        this.e = data1;
      }, error => console.log(error));
    }, error => console.log(error));
  }

  loadHome() {
    // tslint:disable-next-line: radix
    this.id = parseInt(this.route.snapshot.paramMap.get('id'));
    this.router.navigate(['empDash/', this.id]);
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

  }

  acceptBooking(bId: number) {
    this.book = new Booking();

    // tslint:disable-next-line: deprecation
    this.bookingService.getBookingById(bId).subscribe(data => {
      console.log(data);
      this.book = data;

      // tslint:disable-next-line: deprecation
      this.employeeService.acceptDenyBooking(this.book, 'ACCEPTED', this.book.totalAmt).subscribe(info => {
        console.log(info);
        alert(info);
        this.loadData();
      });
    });
  }

  denyBooking(bId: number) {
    this.book = new Booking();

    // tslint:disable-next-line: deprecation
    this.bookingService.getBookingById(bId).subscribe(data => {
      console.log(data);
      this.book = data;

      // tslint:disable-next-line: deprecation
      this.employeeService.acceptDenyBooking(this.book, 'DENIED', this.book.totalAmt).subscribe(info => {
        console.log(info);
        alert(info);
        this.loadData();
      });
    });
  }
}
