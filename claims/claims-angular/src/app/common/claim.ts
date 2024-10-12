// src/app/models/claim.model.ts

export class Claim {
  id?: number; // Optional for new claims
  claimantName: string;
  claimantContact: string;
  accidentDetails: string;
  accidentDate: Date; // Use Date type to represent date
  claimSubmissionDate?: Date; // Optional, can be set automatically on the backend
  claimStatus?: string; // Optional, can be set automatically on the backend
  insurancePolicyNumber: string;
  claimAmount: number;
  claimProcessingNotes?: string; // Optional, to store any processing notes

  constructor(
    claimantName: string,
    claimantContact: string,
    accidentDetails: string,
    accidentDate: Date,
    insurancePolicyNumber: string,
    claimAmount: number,
    claimSubmissionDate?: Date,
    claimStatus?: string
  ) {
    this.claimantName = claimantName;
    this.claimantContact = claimantContact;
    this.accidentDetails = accidentDetails;
    this.accidentDate = accidentDate;
    this.insurancePolicyNumber = insurancePolicyNumber;
    this.claimAmount = claimAmount;
    this.claimSubmissionDate = claimSubmissionDate;
    this.claimStatus = claimStatus;
  }
}
