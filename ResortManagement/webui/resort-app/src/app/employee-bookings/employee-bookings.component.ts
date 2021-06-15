import { Booking } from './../place-booking/booking';
import { Employee } from './../employeedash/employee';
import { BookingService } from './../booking.service';
import { EmployeeService } from './../employee.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-employee-bookings',
  templateUrl: './employee-bookings.component.html',
  styleUrls: ['./employee-bookings.component.css']
})
export class EmployeeBookingsComponent implements OnInit {

  id: number;
  e: Employee = new Employee();
  bookings: Booking[];

  constructor(private router: Router, private route: ActivatedRoute,
              private employeeService: EmployeeService, private bookingService: BookingService) { }

  ngOnInit(): void {
    this.loadData();
  }

  loadData() {
    // tslint:disable-next-line: radix
    this.id = parseInt(this.route.snapshot.paramMap.get('id'));

    // tslint:disable-next-line: deprecation
    this.employeeService.getEmployeeById(this.id).subscribe(info => {
      this.e = info;
    }, error => console.log(error));

    // tslint:disable-next-line: deprecation
    this.bookingService.getBookingsForEmployee(this.id).subscribe(data => {
      console.log(data);
      this.bookings = data;

      for (const b of this.bookings) {
        // tslint:disable-next-line: deprecation
        this.bookingService.getBookingAmount(b.bookingId).subscribe(info => {
          b.totalAmt = info;
        }, error => console.log(error));
      }
    }, error => console.log(error));
  }

  showDetails() {
    // tslint:disable-next-line: radix
    this.id = parseInt(this.route.snapshot.paramMap.get('id'));
    this.router.navigate(['eDetails/', this.id]);
  }

  changePassword() {

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

  showBookingDetails(bId: number) {
    // tslint:disable-next-line: radix
    this.id = parseInt(this.route.snapshot.paramMap.get('id'));
    this.router.navigate(['purchaseDets/', bId]);
  }

  showPendingBookings() {
    // tslint:disable-next-line: radix
    this.id = parseInt(this.route.snapshot.paramMap.get('id'));
    this.router.navigate(['pending/', this.id]);
  }

  addCoupon() {}
}
