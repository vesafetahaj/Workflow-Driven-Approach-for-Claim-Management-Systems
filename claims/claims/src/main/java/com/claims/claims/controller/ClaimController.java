package com.claims.claims.controller;

import com.claims.claims.models.AccidentClaim;
import com.claims.claims.services.AccidentClaimService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/claims")
public class ClaimController {

    @Autowired
    private AccidentClaimService accidentClaimService; // Inject AccidentClaimService

    @PostMapping("/submit")
    public ResponseEntity<String> submitClaim(@RequestBody AccidentClaim accidentClaim) {
        try {
            String responseMessage = accidentClaimService.submitClaim(accidentClaim);
            return new ResponseEntity<>(responseMessage, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            // Log the error and return the message to the client
            e.printStackTrace(); // Log the stack trace for better debugging
            return new ResponseEntity<>("Error submitting claim: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            // Handle any unexpected exceptions
            e.printStackTrace();
            return new ResponseEntity<>("Unexpected error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<AccidentClaim> saveAccidentClaim(@RequestBody AccidentClaim accidentClaim) {
        try {
            AccidentClaim savedClaim = accidentClaimService.saveAccidentClaim(accidentClaim);
            return new ResponseEntity<>(savedClaim, HttpStatus.CREATED);
        } catch (Exception e) {
            // Handle any exceptions and return a proper error response
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/check-errors")
    public ResponseEntity<String> checkValidationErrors(@RequestParam String processInstanceId) {
        return accidentClaimService.checkValidationErrors(processInstanceId);
    }

}
