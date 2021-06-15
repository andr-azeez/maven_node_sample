import { Amenities } from './amenity-details/amenities';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AmenityService {

  private baseUrl = 'http://localhost:8080/ResortManagement/api/amenities';

  constructor(private http: HttpClient) { }

  getAllAmenities(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }

  getAmenityById(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  getAmenityByName(name: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/byname/${name}`);
  }

  getByCategory(cat: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/category/${cat}`);
  }

  getListByEmployee(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/employee/${id}`);
  }

  updatePrice(am: Amenities, p: number): Observable<any> {
    const header = {'content-type': 'application/json'};
    const body = JSON.stringify(am);

    return this.http.put(`${this.baseUrl}/update/${am.amenityId}/${p}`,
    body, {headers: header, responseType: 'text'});
  }

  addNewAmentiy(am: Amenities): Observable<any> {
    const header = {'content-type': 'application/json'};
    const body = JSON.stringify(am);

    return this.http.post(`${this.baseUrl}/addnew/${am.amenityName}/${am.price}/${am.employeeId}/${am.amenityCategory}`,
    body, {headers: header, responseType: 'text'});
  }
}
