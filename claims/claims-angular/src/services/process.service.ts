import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProcessService {

  private baseUrl = 'http://localhost:8080/api/process'; // Adjust the URL as needed

  constructor(private http: HttpClient) { }

  startProcess(formData: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/start`, formData);
  }
}
