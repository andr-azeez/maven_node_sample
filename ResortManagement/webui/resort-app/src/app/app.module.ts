import { TooltipDirective } from './tooltip.directive';
import { CustomDialogModule } from './custom-dialog/custom-dialog.module';
import { AppRoutingModule } from './app-routing.module';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { BackButtonDisableModule } from 'angular-disable-browser-back-button';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';


import { MemberService } from './member.service';
import { CouponService } from './coupon.service';
import { AmenityService } from './amenity.service';
import { BookingService } from './booking.service';
import { EmployeeService } from './employee.service';

import { InlineEditComponent } from './inline-edit/inline-edit.component';

import { AppComponent } from './app.component';
import { MemberLoginComponent } from './memberlogin/memberlogin.component';
import { HomeComponent } from './home/home.component';
import { EmployeeLoginComponent } from './employee-login/employee-login.component';
import { MemberdashComponent } from './memberdash/memberdash.component';
import { EmployeedashComponent } from './employeedash/employeedash.component';
import { ContactComponent } from './contact/contact.component';
import { RegisterComponent } from './register/register.component';
import { MainpageComponent } from './mainpage/mainpage.component';
import { MemberDetailsComponent } from './member-details/member-details.component';
import { EmployeeDetailsComponent } from './employee-details/employee-details.component';
import { MemberBookingsComponent } from './member-bookings/member-bookings.component';
import { EmployeeBookingsComponent } from './employee-bookings/employee-bookings.component';
import { PendingBookingsComponent } from './pending-bookings/pending-bookings.component';
import { CurrentBookingsComponent } from './current-bookings/current-bookings.component';
import { PlaceBookingComponent } from './place-booking/place-booking.component';
import { BookingDetailsComponent } from './booking-details/booking-details.component';
import { AmenityDetailsComponent } from './amenity-details/amenity-details.component';
import { AddAmenityComponent } from './add-amenity/add-amenity.component';
import { CouponComponent } from './coupon/coupon.component';
import { ChangePassComponent } from './change-pass/change-pass.component';
import { PurchaseDetailsComponent } from './purchase-details/purchase-details.component';


@NgModule({
  declarations: [
    AppComponent,
    MemberLoginComponent,
    HomeComponent,
    EmployeeLoginComponent,
    MemberdashComponent,
    EmployeedashComponent,
    ContactComponent,
    RegisterComponent,
    MainpageComponent,
    MemberDetailsComponent,
    EmployeeDetailsComponent,
    MemberBookingsComponent,
    EmployeeBookingsComponent,
    PendingBookingsComponent,
    CurrentBookingsComponent,
    PlaceBookingComponent,
    BookingDetailsComponent,
    AmenityDetailsComponent,
    AddAmenityComponent,
    CouponComponent,
    InlineEditComponent,
    ChangePassComponent,
    TooltipDirective,
    PurchaseDetailsComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    CustomDialogModule,
    HttpClientModule,
    BackButtonDisableModule.forRoot({
      preserveScrollPosition: true
    }),
    AppRoutingModule
  ],
  providers: [
    MemberService,
    EmployeeService,
    BookingService,
    AmenityService,
    CouponService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
