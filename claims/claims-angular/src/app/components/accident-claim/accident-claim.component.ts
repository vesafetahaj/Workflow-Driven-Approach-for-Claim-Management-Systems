// src/app/accident-claim/accident-claim.component.ts
import { Component } from '@angular/core';
import { AccidentClaimService } from '../../services/accident-claim.service';
import axios from "axios";
import {Claim} from "../../common/claim";

@Component({
  selector: 'app-accident-claim',
  templateUrl: './accident-claim.component.html',
  styleUrls: ['./accident-claim.component.css'] // Optional: CSS for styling
})
export class AccidentClaimComponent {
  // claimantName: string = '';
  // accidentDetails: string = '';
  // successMessage: string = '';
  // errorMessage: string = '';
  //
  // constructor(private accidentClaimService: AccidentClaimService) {}
  //
  // submitClaim() {
  //   const claim = {
  //     claimantName: this.claimantName,
  //     accidentDetails: this.accidentDetails
  //   };
  //
  //   this.accidentClaimService.submitAccidentClaim(claim).subscribe({
  //     next: (response) => {
  //       console.log('Claim submitted:', response);
  //       this.successMessage = 'Claim submitted successfully!';
  //       this.errorMessage = '';
  //       this.resetForm(); // Reset form after successful submission
  //     },
  //     error: (error) => {
  //       console.error('Error submitting claim:', error);
  //       this.errorMessage = 'Failed to submit claim. Please try again.';
  //       this.successMessage = '';
  //     }
  //   });
  // }
  //
  // resetForm() {
  //   this.claimantName = '';
  //   this.accidentDetails = '';
  // }
  claimData: Claim = new Claim('', '', '', new Date(), '', 0); // Initialize the Claim object

  constructor(private accidentClaimService: AccidentClaimService) {}

  submitClaim() {
    this.accidentClaimService.submitClaim(this.claimData).subscribe(
      (response) => {
        // Handle successful submission
        alert('Claim submitted successfully: ' + response);
        // Reset the form
        this.claimData = new Claim('', '', '', new Date(), '', 0);
      },
      (error) => {
        // Handle error response
        alert('Error submitting claim: ' + error.error.message);
      }
    );
  }
}
