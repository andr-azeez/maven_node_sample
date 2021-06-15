import { CustomDialogService } from './../custom-dialog/custom-dialog.service';
import { MemberService } from './../member.service';
import { Amenities } from '../amenity-details/amenities';
import { AmenityService } from '../amenity.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { BookingService } from '../booking.service';
import { Booking } from '../place-booking/booking';
import { Observable } from 'rxjs';
import { Member } from '../memberdash/member';

@Component({
  selector: 'app-current-bookings',
  templateUrl: './current-bookings.component.html',
  styleUrls: ['./current-bookings.component.css']
})
export class CurrentBookingsComponent implements OnInit {

  test: string;
  memId: number;
  tot: number;
  bDate: string;
  amt: number;
  amId: number;
  am1: Amenities = new Amenities();
  // list: Observable<Booking[]>;
  list: Booking[];
  am: Amenities = new Amenities();
  m: Member = new Member();
  b: Booking;

  constructor(private router: Router, private route: ActivatedRoute,
              private bookingService: BookingService, private amenityService: AmenityService,
              private memberService: MemberService, private dialogService: CustomDialogService) { }

  ngOnInit(): void {
    this.loadData();
  }

  loadData() {

    this.test = this.route.snapshot.paramMap.get('id');
    console.log(this.test);
    // tslint:disable-next-line: radix
    this.memId = parseInt(this.route.snapshot.paramMap.get('id'));

    // tslint:disable-next-line: deprecation
    this.memberService.getMemberById(this.memId).subscribe(one => {
      console.log(one);
      this.m = one;
    });

    // tslint:disable-next-line: deprecation
    this.bookingService.getCurrentBookings(this.memId).subscribe(data => {
      console.log(data);
      this.list = data;

      for (const l of this.list) {
        this.bDate = l.bookingDate;

        // tslint:disable-next-line: deprecation
        this.bookingService.getBookingAmount(l.bookingId).subscribe(data1 => {
          console.log('Amount ' + data1);
          this.amt = data1;
        }, error => console.log(error));

        // tslint:disable-next-line: deprecation
        this.amenityService.getAmenityById(l.amenityId).subscribe(info => {
          console.log('Amenity: ', info);
          this.am1 = info;
        }, error => console.log(error));
      }
      // data.forEach(element => {
      //   this.bDate = element.bookingDate;
      //   // this.amId = element.amenityId;
      //   // tslint:disable-next-line: deprecation
      //   this.bookingService.getBookingAmount(element.bookingId).subscribe(data1 => {
      //     console.log('Amount ' + data1);
      //     this.amt = data1;
      //   }, error => console.log(error));

      //   // tslint:disable-next-line: deprecation
      //   this.amenityService.getAmenityById(element.amenityId).subscribe(info => {
      //     console.log('Amenity: ', info);
      //     this.am1 = info;
      //   }, error => console.log(error));
      // });
    });
  }

  loadHome(): void {
    // tslint:disable-next-line: radix
    this.memId = parseInt(this.route.snapshot.paramMap.get('id'));
    // alert(this.val);
    this.router.navigate(['home/', this.memId]);
  }

  showDetails() {
    // tslint:disable-next-line: radix
    this.memId = parseInt(this.route.snapshot.paramMap.get('id'));
    this.router.navigate(['details/', this.memId]);
  }

  changePassword() {
    // tslint:disable-next-line: radix
    this.memId = parseInt(this.route.snapshot.paramMap.get('id'));
    this.router.navigate(['pass/', this.memId]);
  }

  editBooking(bId: number, qty: number) {
    this.b = new Booking();
    // tslint:disable-next-line: deprecation
    this.bookingService.getBookingById(bId).subscribe(data => {
      this.b = data;
      // tslint:disable-next-line: deprecation
      this.bookingService.updateQuantity(this.b, qty).subscribe(one => {
        console.log(one);

        if (one === 'Quantity updated successfully') {
          // tslint:disable-next-line: deprecation
          this.amenityService.getAmenityById(this.b.amenityId).subscribe(info => {
            this.am = info;
            this.tot = this.am.price * qty;
            console.log(this.tot);
            // tslint:disable-next-line: deprecation
            this.bookingService.updateTotalAmount(this.b, this.tot).subscribe(res => {
              console.log(res);
              if (res === 'Amount updated successfully') {
                alert('Your Booking Has been Updated!');
                this.loadData();
              }
            });
          });
        }
      });
    });
  }

  cancelBooking(bId: number) {
    // tslint:disable-next-line: deprecation
    this.memberService.cancelBooking(this.m, bId).subscribe(data => {
      console.log(data);
      if (data === 'Booking Cancelled Successfully') {
        alert('Booking Cancelled');
      } else {
        alert('Cancellation Unsuccessful! Please try again later!');
      }
      this.loadData();
    });
  }

  openDialog(bId: number) {
    const options = {
      title: 'Cancel Booking?',
      message: 'This Action Is Irreversible! Are you sure you want to cancel?'
    };

    this.dialogService.open(options);

    // tslint:disable-next-line: deprecation
    this.dialogService.confirmed().subscribe(confirmed => {
      if (confirmed) {
        this.cancelBooking(bId);
      } else {
        alert('Booking Has Not Been Cancelled!');
        this.loadData();
      }
    });
  }

  showAllBookings() {
    // tslint:disable-next-line: radix
    this.memId = parseInt(this.route.snapshot.paramMap.get('id'));
    this.router.navigate(['history/', this.memId]);
  }

  showFeedback() {
    // tslint:disable-next-line: radix
    this.memId = parseInt(this.route.snapshot.paramMap.get('id'));
    this.router.navigate(['contact/', this.memId]);
  }
}
