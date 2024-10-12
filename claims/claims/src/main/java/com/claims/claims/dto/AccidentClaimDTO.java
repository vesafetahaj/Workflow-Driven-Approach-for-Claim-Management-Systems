package com.claims.claims.dto;
import com.claims.claims.models.AccidentClaim;
import com.claims.claims.models.enums.ClaimStatus;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class AccidentClaimDTO implements Serializable {
    private String claimantName;
    private String claimantContact;
    private String accidentDetails;
    private LocalDate accidentDate;
    private LocalDate claimSubmissionDate; // If needed
    private ClaimStatus claimStatus; // If needed
    private String insurancePolicyNumber;
    private Double claimAmount;

    // Constructor to convert from AccidentClaim
    public AccidentClaimDTO(AccidentClaim accidentClaim) {
        this.claimantName = accidentClaim.getClaimantName();
        this.claimantContact = accidentClaim.getClaimantContact();
        this.accidentDetails = accidentClaim.getAccidentDetails();
        this.accidentDate = accidentClaim.getAccidentDate();
        this.claimSubmissionDate = accidentClaim.getClaimSubmissionDate();
        this.claimStatus = accidentClaim.getClaimStatus();
        this.insurancePolicyNumber = accidentClaim.getInsurancePolicyNumber();
        this.claimAmount = accidentClaim.getClaimAmount();
    }
}