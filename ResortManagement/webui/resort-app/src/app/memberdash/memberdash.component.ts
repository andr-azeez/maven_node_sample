import { MemberService } from './../member.service';
import { AmenityService } from './../amenity.service';
import { Observable } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { Amenities } from '../amenity-details/amenities';

@Component({
  selector: 'app-memberdash',
  templateUrl: './memberdash.component.html',
  styleUrls: ['./memberdash.component.css']
})
export class MemberdashComponent implements OnInit {

  val1: number;
  amenity: string;
  amList: Observable<Amenities[]>;
  selected = '';

  catList: string[];

  constructor(private router: Router, private route: ActivatedRoute,
              private amenityService: AmenityService, private memberService: MemberService) { }

  ngOnInit(): void {
    // tslint:disable-next-line: no-string-literal
    this.val1 = this.route.params['id'];
    this.amenity = this.route.snapshot.paramMap.get('val');
    this.amenity = this.amenity.toUpperCase();

    this.loadData(this.amenity);
  }

  loadData(cat: string) {
    if (cat === 'NEW' || cat === 'ALL') {
      this.amenity = 'ALL';
      // tslint:disable-next-line: deprecation
      this.amenityService.getAllAmenities().subscribe(data => {
        this.amList = data;
      });
    } else {
      // tslint:disable-next-line: deprecation
      this.amenityService.getByCategory(cat).subscribe(data => {
        this.amList = data;
      });
    }
  }

  loadHome(): void {
    // tslint:disable-next-line: radix
    this.val1 = parseInt(this.route.snapshot.paramMap.get('id'));
    // alert(this.val);
    this.router.navigate(['home/', this.val1]);
  }

  book(ser: number) {
    // tslint:disable-next-line: radix
    this.val1 = parseInt(this.route.snapshot.paramMap.get('id'));
    // alert(this.val);
    this.router.navigate(['book/', this.val1, ser]);
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

  showAllBookings() {
    // tslint:disable-next-line: radix
    this.val1 = parseInt(this.route.snapshot.paramMap.get('id'));
    this.router.navigate(['history/', this.val1]);
  }

  showService(amId: number) {
    // tslint:disable-next-line: radix
    this.val1 = parseInt(this.route.snapshot.paramMap.get('id'));
    this.router.navigate(['amDetails/', this.val1, amId]);
  }
}
