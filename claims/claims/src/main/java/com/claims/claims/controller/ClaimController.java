package com.claims.claims.controller;

import com.claims.claims.dto.ApiResponse;
import com.claims.claims.models.AccidentClaim;
import com.claims.claims.services.AccidentClaimService;
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
    public ResponseEntity<ApiResponse> submitClaim(@RequestBody AccidentClaim accidentClaim) {
        try {
            // Start the claim process
            String responseMessage = accidentClaimService.submitClaim(accidentClaim);
            ApiResponse response = new ApiResponse(responseMessage, null);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ApiResponse("Validation error: " + e.getMessage(), null), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ApiResponse("Unexpected error: " + e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<AccidentClaim> saveAccidentClaim(@RequestBody AccidentClaim accidentClaim) {
        try {
            AccidentClaim savedClaim = accidentClaimService.saveAccidentClaim(accidentClaim);
            return new ResponseEntity<>(savedClaim, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
