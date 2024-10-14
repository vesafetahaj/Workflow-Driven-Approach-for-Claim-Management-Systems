package com.claims.claims.services;

import com.claims.claims.models.AccidentClaim;
import com.claims.claims.models.enums.ClaimStatus;
import com.claims.claims.repository.AccidentClaimRepository;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

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

    public String submitClaim(AccidentClaim accidentClaim) {
        // Validate accident date
        if (accidentClaim.getAccidentDate().isAfter(LocalDate.now())) {
            // Print a specific message for validation failure
            System.out.println("ValidationSteps not passed, aborted.");
            return "ValidationSteps not passed, aborted."; // Return the message instead of throwing an exception
        }

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

        // Check the validation result
        Boolean isValidData = (Boolean) runtimeService.getVariable(processInstance.getId(), "isValidData");
        if (isValidData != null && !isValidData) {
            // Print a specific message for validation failure
            System.out.println("ValidationSteps not passed, aborted.");
            return "ValidationSteps not passed, aborted."; // Return the message instead of throwing an exception
        }

        // Return the process instance ID
        return "Claim submitted successfully! Process ID: " + processInstance.getId();
    }


    public AccidentClaim findClaimByUniqueIdentifier(String insurancePolicyNumber) {
        return accidentClaimRepository.findByInsurancePolicyNumber(insurancePolicyNumber);
    }

    public ResponseEntity<String> checkValidationErrors(String processInstanceId) {
        // Retrieve process instance and check for validation errors
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                .processInstanceId(processInstanceId)
                .singleResult();

        if (processInstance != null) {
            String validationErrors = (String) runtimeService.getVariable(processInstance.getId(), "validationErrors");
            if (validationErrors != null && !validationErrors.isEmpty()) {
                return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>("No validation errors.", HttpStatus.OK);
    }
}
