import { CouponService } from './../coupon.service';
import { Amenities } from './../amenity-details/amenities';
import { AmenityService } from './../amenity.service';
import { EmployeeService } from './../employee.service';
import { Employee } from './../employeedash/employee';
import { Router, ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { Coupon } from './coupon';

@Component({
  selector: 'app-coupon',
  templateUrl: './coupon.component.html',
  styleUrls: ['./coupon.component.css']
})
export class CouponComponent implements OnInit {

  id: number;
  e: Employee;
  name: string;
  expiry: string;
  cat: Amenities;
  discount: number;
  amList: Amenities[];
  am: Amenities;
  coupon: Coupon;
  amName: string;
  ans: boolean;

  constructor(private router: Router, private route: ActivatedRoute,
              private empService: EmployeeService, private amService: AmenityService,
              private couponService: CouponService) { }

  ngOnInit(): void {
    // tslint:disable-next-line: radix
    this.id = parseInt(this.route.snapshot.paramMap.get('id'));

    // tslint:disable-next-line: deprecation
    this.empService.getEmployeeById(this.id).subscribe(data => {
      console.log(data);
      this.e = data;
    });

    this.loadPage();
  }

  loadPage() {
    // tslint:disable-next-line: radix
    this.id = parseInt(this.route.snapshot.paramMap.get('id'));

    // tslint:disable-next-line: deprecation
    this.empService.getEmployeeById(this.id).subscribe(data => {
      console.log(data);
      this.e = data;
    });

    // tslint:disable-next-line: deprecation
    this.amService.getAllAmenities().subscribe(res => {
      this.amList = res;
    });

  }
  loadHome() {
    // tslint:disable-next-line: radix
    this.id = parseInt(this.route.snapshot.paramMap.get('id'));
    this.router.navigate(['empDash/', this.id]);
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

  addService() {
    // tslint:disable-next-line: radix
    this.id = parseInt(this.route.snapshot.paramMap.get('id'));
    this.router.navigate(['newAm/', this.id]);
  }

  addCoupon() {
    this.coupon = new Coupon();
    this.coupon.couponId = this.name;
    this.coupon.expiryDate = this.expiry;
    this.coupon.discount = this.discount;
    this.coupon.amenityId = 0;
    this.amName = this.selectedOption();

    // tslint:disable-next-line: deprecation
    this.couponService.addNewCoupon(this.coupon, this.amName).subscribe(data => {
      console.log(data);
      alert(data);
      this.ans = confirm('Add another coupon?');
      // console.log('Answer: ', this.ans);
      if (this.ans === true) {
        this.loadPage();
      } else {
        this.loadHome();
      }
    });
  }

  selectedOption(): string{
    console.log(this.cat.amenityName);
    return this.cat.amenityName;
  }
}
