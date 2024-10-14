import { Component } from '@angular/core';
import { AccidentClaimService } from '../../services/accident-claim.service';
import { Claim } from "../../common/claim";
import {animate, style, transition, trigger} from "@angular/animations";

@Component({
  selector: 'app-accident-claim',
  templateUrl: './accident-claim.component.html',
  styleUrls: ['./accident-claim.component.css'],
  animations: [
    trigger('fadeInOut', [
      transition(':enter', [
        style({ opacity: 0 }),  // Start fully transparent
        animate('0.5s', style({ opacity: 1 })),  // Fade in
      ]),
      transition(':leave', [
        animate('0.5s', style({ opacity: 0 })),  // Fade out
      ]),
    ]),
  ],
})
export class AccidentClaimComponent {
  // Initialize the Claim object with default values
  claimData: Claim = new Claim('', '', '', new Date(), '', 0);

  // Arrays to store validation error messages and success messages
  errorMessages: string[] = [];
  successMessage: string = '';

  // Store today's date in YYYY-MM-DD format for the max attribute in the date input
  todayDate: string;
  isSubmitting: boolean = false;

  constructor(private accidentClaimService: AccidentClaimService) {
    const today = new Date();
    const year = today.getFullYear();
    const month = ('0' + (today.getMonth() + 1)).slice(-2);
    const day = ('0' + today.getDate()).slice(-2);
    this.todayDate = `${year}-${month}-${day}`;
  }

  /**
   * Validates the form data before submission.
   * @returns {boolean} - Returns true if the form is valid, otherwise false.
   */
  validateForm(): boolean {
    this.errorMessages = []; // Clear previous errors
    let isValid = true;

    // Validate claimant name
    if (!this.claimData.claimantName || this.claimData.claimantName.trim() === '') {
      this.errorMessages.push('Claimant name is required.');
      isValid = false;
    }

    // Validate claimant contact (simple phone number validation)
    const contactRegex = /^\+?[0-9]{10,15}$/;
    if (!this.claimData.claimantContact || !contactRegex.test(this.claimData.claimantContact)) {
      this.errorMessages.push('Invalid claimant contact. Must be a valid phone number (10-15 digits, optional +).');
      isValid = false;
    }

    // Validate accident details
    if (!this.claimData.accidentDetails || this.claimData.accidentDetails.trim() === '') {
      this.errorMessages.push('Accident details are required.');
      isValid = false;
    }

    // Validate accident date
    if (!this.claimData.accidentDate || new Date(this.claimData.accidentDate) > new Date()) {
      this.errorMessages.push('Accident date cannot be in the future.');
      isValid = false;
    }

    // Validate insurance policy number
    if (!this.claimData.insurancePolicyNumber || this.claimData.insurancePolicyNumber.trim() === '') {
      this.errorMessages.push('Insurance policy number is required.');
      isValid = false;
    }

    // Validate claim amount
    if (this.claimData.claimAmount === null || this.claimData.claimAmount <= 0) {
      this.errorMessages.push('Claim amount must be a positive number.');
      isValid = false;
    }

    return isValid;
  }

  /**
   * Handles the form submission.
   * Validates the form and submits the claim if valid.
   */
  submitClaim() {
    this.successMessage = ''; // Clear success message

    // Validate the form before submission
    const isValid = this.validateForm(); // Store validation result

    if (!isValid) {
      return; // Exit if the form is invalid
    }

    this.isSubmitting = true; // Show loading indicator

    this.accidentClaimService.submitClaim(this.claimData).subscribe(
      (response) => {
        this.successMessage = response.message || 'Claim submitted successfully!';
        this.isSubmitting = false; // Hide loading indicator
        setTimeout(() => {
          this.successMessage = ''; // Clear the success message
        }, 1500);
        // Reset the form after successful submission
        this.claimData = new Claim('', '', '', new Date(), '', 0);
        this.errorMessages = []; // Clear error messages after submission
      },
      (error) => {
        console.error('Submission Error:', error);
        let errorMessage = 'Failed to submit claim. Please try again.';
        if (error.error && error.error.message) {
          errorMessage = error.error.message;
        } else if (error.message) {
          errorMessage = error.message;
        } else if (error.status === 0) {
          errorMessage = 'Network error: Please check your internet connection.';
        }
        this.errorMessages = [errorMessage]; // Set error messages
        this.successMessage = ''; // Ensure success message is cleared
        this.isSubmitting = false; // Hide loading indicator
      }
    );
  }



}
