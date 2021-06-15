import { Booking } from './place-booking/booking';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class BookingService {

  private baseUrl = 'http://localhost:8080/ResortManagement/api/bookings';
  constructor(private http: HttpClient) { }

  getCurrentBookings(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/current/${id}`);
  }

  getBookingAmount(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/getAmount/${id}`);
  }

  getBookingById(bid: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${bid}`);
  }

  updateQuantity(b: Booking, qty: number): Observable<any> {
    const header = {'content-type': 'application/json'};
    const body = JSON.stringify(b);

    return this.http.put(`${this.baseUrl}/quantity/${b.bookingId}/${qty}`,
    body, {headers: header, responseType: 'text'});
  }

  updateTotalAmount(b: Booking, tot: number): Observable<any> {
    const header = {'content-type': 'application/json'};
    const body = JSON.stringify(b);

    return this.http.put(`${this.baseUrl}/total/${b.bookingId}/${tot}`,
    body, {headers: header, responseType: 'text'});
  }

  getPendingBookings(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/pending/${id}`);
  }

  getBookingsForEmployee(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/eHistory/${id}`);
  }
}
