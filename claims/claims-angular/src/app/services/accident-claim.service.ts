  // src/app/accident-claim.service.ts
  import { Injectable } from '@angular/core';
  import { HttpClient } from '@angular/common/http';
  import { Observable } from 'rxjs';

  interface AccidentClaim {
    claimantName: string;
    accidentDetails: string;
  }

  @Injectable({
    providedIn: 'root',
  })
  export class AccidentClaimService {
    // private baseUrl = 'http://localhost:8080/api/accident-claims';
    //
    // constructor(private http: HttpClient) {}
    //
    // submitAccidentClaim(claim: AccidentClaim): Observable<AccidentClaim> {
    //   return this.http.post<AccidentClaim>(this.baseUrl, claim);
    // }
    private apiUrl = 'http://localhost:8080/api/claims';

    constructor(private http: HttpClient) { }

    submitClaim(claim: any): Observable<any> {
      return this.http.post(`${this.apiUrl}/submit`, claim);
    }
  }
