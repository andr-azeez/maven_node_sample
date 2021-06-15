import { Booking } from './place-booking/booking';
import { Employee } from './employeedash/employee';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  private baseUrl = 'http://localhost:8080/ResortManagement/api/employees';
  constructor(private http: HttpClient) { }

  getEmployeeById(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  getEmployeeByEmail(em: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/check/${em}`);
  }

  registerEmployee(emp: Employee): Observable<any> {
    const header = {'content-type': 'application/json'};
    const body = JSON.stringify(emp);

    return this.http.post(`${this.baseUrl}/addnew/${emp.employeeName}/${emp.phone}`
      + `/${emp.email}/${emp.passKey}`, body, {headers: header, responseType: 'text'});
  }

  updatePhone(emp: Employee, ph: string): Observable<any> {
    const header = {'content-type': 'application/json'};
    const body = JSON.stringify(emp);

    return this.http.put(`${this.baseUrl}/phone/${emp.employeeId}/${ph}`,
          body, {headers: header, responseType: 'text'});
  }

  updatePassword(emp: Employee, p: string): Observable<any> {
    const header = {'content-type': 'application/json'};
    const body = JSON.stringify(emp);

    return this.http.put(`${this.baseUrl}/password/${emp.employeeId}/${p}`,
          body, {headers: header, responseType: 'text'});
  }

  acceptDenyBooking(b: Booking, stat: string, amt: number): Observable<any> {
    const header = {'content-type': 'application/json'};
    const body = JSON.stringify(b);

    return this.http.put(`${this.baseUrl}/acceptdeny/${b.bookingId}/${stat}/${amt}`,
          body, {headers: header, responseType: 'text'});
  }
}
