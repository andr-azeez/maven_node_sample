import { Observable } from 'rxjs';
import { MemberService } from './../member.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { Booking } from '../place-booking/booking';
import { Member } from '../memberdash/member';

@Component({
  selector: 'app-member-bookings',
  templateUrl: './member-bookings.component.html',
  styleUrls: ['./member-bookings.component.css']
})
export class MemberBookingsComponent implements OnInit {

  id: number;
  m: Member;
  bookings: Observable<Booking[]>;

  constructor(private router: Router, private route: ActivatedRoute,
              private memberService: MemberService) { }

  ngOnInit(): void {
    this.loadData();
  }

  loadData() {
    this.m = new Member();
    // tslint:disable-next-line: radix
    this.id = parseInt(this.route.snapshot.paramMap.get('id'));

    // tslint:disable-next-line: deprecation
    this.memberService.getMemberById(this.id).subscribe(one => {
      console.log(one);
      this.m = one;
    });

    // tslint:disable-next-line: deprecation
    this.memberService.getBookingHistory(this.id).subscribe(data => {
      console.log(data);
      this.bookings = data;
    });
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

  showBookingDetails(bId: number) {

  }
}
