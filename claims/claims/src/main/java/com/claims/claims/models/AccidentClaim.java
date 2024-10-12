package com.claims.claims.models;// src/main/java/com/example/claims/model/AccidentClaim.java


import com.claims.claims.models.enums.ClaimStatus;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Entity
@Table(name = "claims")
@Data
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccidentClaim implements Serializable {  // Implement Serializable
    private static final long serialVersionUID = 1L;  // Add a serialVersionUID

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String claimantName;
    private String claimantContact;
    private String accidentDetails;
    private LocalDate accidentDate;
    private LocalDate claimSubmissionDate;

    @Enumerated(EnumType.STRING)
    private ClaimStatus claimStatus;

    private String insurancePolicyNumber;
    private Double claimAmount;
    private String claimProcessingNotes;

    public AccidentClaim(String claimantName, String accidentDetails, LocalDate accidentDate, String insurancePolicyNumber, Double claimAmount) {
        this.claimantName = claimantName;
        this.accidentDetails = accidentDetails;
        this.accidentDate = accidentDate;
        this.insurancePolicyNumber = insurancePolicyNumber;
        this.claimAmount = claimAmount;
        this.claimSubmissionDate = LocalDate.now();
        this.claimStatus = ClaimStatus.PENDING;  // Default status
    }
}
