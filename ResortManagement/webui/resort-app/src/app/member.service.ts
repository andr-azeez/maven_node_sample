import { Booking } from './place-booking/booking';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Member } from './memberdash/member';


@Injectable({
  providedIn: 'root'
})
export class MemberService {

  private baseUrl = 'http://localhost:8080/ResortManagement/api/members';

  constructor(private http: HttpClient) { }

  /**
   * get member by id.
   * @param id member id
   * @returns observable
   */
  getMemberById(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  getMemberByEmail(em: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/check/${em}`);
  }

  registerMember(member: Member): Observable<any> {
    const header = {'content-type': 'application/json'};
    const body = JSON.stringify(member);

    return this.http.post(`${this.baseUrl}/addnew/${member.memberName}/${member.phone}`
      + `/${member.email}/${member.passKey}/${member.dateOfBirth}`, body, {headers: header, responseType: 'text'});
  }

  updatePhone(m: Member, ph: string): Observable<any> {
    const header = {'content-type': 'application/json'};
    const body = JSON.stringify(m);

    return this.http.put(`${this.baseUrl}/phone/${m.memberId}/${ph}`,
        body, {headers: header, responseType: 'text'});
  }

  updateEmail(m: Member, email: string): Observable<any> {
    const header = {'content-type': 'application/json'};
    const body = JSON.stringify(m);

    return this.http.put(`${this.baseUrl}/email/${m.memberId}/${email}`,
        body, {headers: header, responseType: 'text'});
  }

  updateWallet(m: Member, bal: number): Observable<any> {
    const header = {'content-type': 'application/json'};
    const body = JSON.stringify(m);

    return this.http.put(`${this.baseUrl}/email/${m.memberId}/${bal}`,
        body, {headers: header, responseType: 'text'});
  }

  updatePassword(m: Member, pass: string): Observable<any> {
    const header = {'content-type': 'application/json'};
    const body = JSON.stringify(m);

    return this.http.put(`${this.baseUrl}/password/${m.memberId}/${pass}`,
        body, {headers: header, responseType: 'text'});
  }

  getBookingHistory(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/history/${id}`);
  }

  cancelBooking(m: Member, id: number): Observable<any> {
    const header = {'content-type': 'application/json'};
    const body = JSON.stringify(m);

    return this.http.put(`${this.baseUrl}/cancel/${id}`,
        body, {headers: header, responseType: 'text'});
  }

  /**
   * to book an amenity.
   * @param m Member
   * @param id amenityId
   * @param qty quantiy
   */
  bookAmenity(m: Member, id: number, qty: number): Observable<any> {
    const header = {'content-type': 'application/json'};
    const body = JSON.stringify(m);

    return this.http.post(`${this.baseUrl}/book/${id}/${qty}/${m.memberId}`,
    body, {headers: header, responseType: 'text'});

  }
}
