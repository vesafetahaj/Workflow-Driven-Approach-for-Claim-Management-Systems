package com.claims.claims.services;

import com.claims.claims.models.AccidentClaim;
import com.claims.claims.models.enums.ClaimStatus;
import com.claims.claims.repository.AccidentClaimRepository;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AccidentClaimService {
    @Autowired
    private AccidentClaimRepository accidentClaimRepository;

    @Autowired
    private RuntimeService runtimeService; // Inject Camunda RuntimeService
    public AccidentClaim saveAccidentClaim(AccidentClaim accidentClaim) {
        accidentClaim.setClaimSubmissionDate(LocalDate.now());
        accidentClaim.setClaimStatus(ClaimStatus.PENDING);
        return accidentClaimRepository.save(accidentClaim);
    }

    public AccidentClaim findClaimByUniqueIdentifier(String insurancePolicyNumber) {
        return accidentClaimRepository.findByInsurancePolicyNumber(insurancePolicyNumber);
    }

    public String submitClaim(AccidentClaim accidentClaim) {
        // Prepare variables to start the process instance
        Map<String, Object> variables = new HashMap<>();

        // Populate variables from the AccidentClaim object
        variables.put("claimantName", accidentClaim.getClaimantName());
        variables.put("claimantContact", accidentClaim.getClaimantContact());
        variables.put("accidentDetails", accidentClaim.getAccidentDetails());
        variables.put("accidentDate", accidentClaim.getAccidentDate());
        variables.put("insurancePolicyNumber", accidentClaim.getInsurancePolicyNumber());
        variables.put("claimAmount", accidentClaim.getClaimAmount());

        // Start the process instance with the provided variables
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("claimProcess", variables);

        // Return success message with the process instance ID
        return "Claim submitted successfully! Process Instance ID: " + processInstance.getId();
    }


//    @Autowired
//    private AccidentClaimRepository claimRepository;
//
//    public AccidentClaim submitClaim(AccidentClaim claim) {
//        claim.setClaimSubmissionDate(LocalDate.now());
//        claim.setClaimStatus(ClaimStatus.PENDING);
//        return claimRepository.save(claim);
//    }
//    @Autowired
//    private RuntimeService runtimeService;
//
//    public AccidentClaim createAccidentClaim(AccidentClaim accidentClaim) {
//        // Save the accident claim to the database
//        AccidentClaim savedClaim = accidentClaimRepository.save(accidentClaim);
//
//        // Start the BPMN process with the claim data
//        runtimeService.startProcessInstanceByKey("AccidentInsuranceClaimProcess",
//                Map.of("claimantName", savedClaim.getClaimantName()));
//
//        return savedClaim;
//    }

}