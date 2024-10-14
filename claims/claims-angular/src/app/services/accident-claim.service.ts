  // src/app/accident-claim.service.ts
  import { Injectable } from '@angular/core';
  import {HttpClient, HttpErrorResponse, HttpHeaders} from '@angular/common/http';
  import {Observable, throwError} from 'rxjs';
  import {catchError} from "rxjs/operators";
  import {Claim} from "../common/claim";

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
    private apiUrl = 'http://localhost:8080/api/claims/submit';
    private getHeaders(): HttpHeaders {
      return new HttpHeaders({
        'Authorization': `Bearer ${localStorage.getItem("accessToken")}`,
        'Content-Type': 'application/json'
      });
    }
    constructor(private http: HttpClient) { }

    submitClaim(claim: Claim): Observable<any> {
      let headers = this.getHeaders();
      return this.http.post<any>(this.apiUrl, claim, { headers });
    }

  }
