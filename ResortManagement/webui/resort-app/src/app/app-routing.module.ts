import { CouponComponent } from './coupon/coupon.component';
import { PurchaseDetailsComponent } from './purchase-details/purchase-details.component';
import { PendingBookingsComponent } from './pending-bookings/pending-bookings.component';
import { EmployeeBookingsComponent } from './employee-bookings/employee-bookings.component';
import { AmenityDetailsComponent } from './amenity-details/amenity-details.component';
import { AddAmenityComponent } from './add-amenity/add-amenity.component';
import { EmployeeDetailsComponent } from './employee-details/employee-details.component';
import { EmployeedashComponent } from './employeedash/employeedash.component';
import { BookingDetailsComponent } from './booking-details/booking-details.component';
import { ChangePassComponent } from './change-pass/change-pass.component';
import { MemberBookingsComponent } from './member-bookings/member-bookings.component';
import { PlaceBookingComponent } from './place-booking/place-booking.component';
import { MemberDetailsComponent } from './member-details/member-details.component';
import { MainpageComponent } from './mainpage/mainpage.component';
import { RegisterComponent } from './register/register.component';
import { ContactComponent } from './contact/contact.component';
import { MemberdashComponent } from './memberdash/memberdash.component';
import { EmployeeLoginComponent } from './employee-login/employee-login.component';
import { HomeComponent } from './home/home.component';
// import { AppComponent } from './app.component';
import { MemberLoginComponent } from './memberlogin/memberlogin.component';
import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CurrentBookingsComponent } from './current-bookings/current-bookings.component';

const routes: Routes = [
  {path: 'home/:id', component: HomeComponent},
  {path: 'login/:val', component: MemberLoginComponent},
  {path: 'signin', component: EmployeeLoginComponent},
  {path: 'memDash/:val/:id', component: MemberdashComponent},
  {path: 'contact/:id', component: ContactComponent},
  {path: 'register/:val', component: RegisterComponent},
  {path: 'main', component: MainpageComponent},
  {path: 'current/:id', component: CurrentBookingsComponent},
  {path: 'details/:id', component: MemberDetailsComponent},
  {path: 'book/:id/:ser', component: PlaceBookingComponent},
  {path: 'history/:id', component: MemberBookingsComponent},
  {path: 'pass/:id', component: ChangePassComponent},
  {path: 'bookDets/:id', component: BookingDetailsComponent},
  {path: 'empDash/:id', component: EmployeedashComponent},
  {path: 'eDetails/:id', component: EmployeeDetailsComponent},
  {path: 'newAm/:id', component: AddAmenityComponent},
  {path: 'amDetails/:mId/:id', component: AmenityDetailsComponent},
  {path: 'eHistory/:id', component: EmployeeBookingsComponent},
  {path: 'pending/:id', component: PendingBookingsComponent},
  {path: 'purchaseDets/:id', component: PurchaseDetailsComponent},
  {path: 'addCoupon/:id', component: CouponComponent},
  {path: '', redirectTo: 'main', pathMatch: 'full'}
];

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
