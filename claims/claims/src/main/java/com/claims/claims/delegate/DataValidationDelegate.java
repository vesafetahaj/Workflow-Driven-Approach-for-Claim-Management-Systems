package com.claims.claims.delegate;

import com.claims.claims.models.AccidentClaim;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.regex.Pattern;

@Component("dataValidation")
public class DataValidationDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) {
        AccidentClaim accidentClaim = (AccidentClaim) execution.getVariable("accidentClaim");
        boolean isValid = true;
        StringBuilder errorMessages = new StringBuilder();

        // Validate claimant name
        if (accidentClaim.getClaimantName() == null || accidentClaim.getClaimantName().isEmpty()) {
            isValid = false;
            errorMessages.append("Claimant name is required. ");
        }

        // Validate claimant contact
        if (accidentClaim.getClaimantContact() == null || !isValidContact(accidentClaim.getClaimantContact())) {
            isValid = false;
            errorMessages.append("Invalid claimant contact. ");
        }

        // Validate accident details
        if (accidentClaim.getAccidentDetails() == null || accidentClaim.getAccidentDetails().isEmpty()) {
            isValid = false;
            errorMessages.append("Accident details are required. ");
        }

        // Validate accident date
        if (accidentClaim.getAccidentDate() == null || accidentClaim.getAccidentDate().isAfter(LocalDate.now())) {
            isValid = false;
            errorMessages.append("Accident date cannot be in the future. ");
        }

        // Validate insurance policy number
        if (accidentClaim.getInsurancePolicyNumber() == null || accidentClaim.getInsurancePolicyNumber().isEmpty()) {
            isValid = false;
            errorMessages.append("Insurance policy number is required. ");
        }

        // Validate claim amount
        if (accidentClaim.getClaimAmount() == null || accidentClaim.getClaimAmount() <= 0) {
            isValid = false;
            errorMessages.append("Claim amount must be a positive number. ");
        }


        if (!isValid) {
            System.out.println("Validation errors: " + errorMessages.toString());
        } else {
            // Print success message if validation is successful
            System.out.println("All validation steps are good.");
        }
        // Set validation result and error messages
        execution.setVariable("isValidData", isValid);
        execution.setVariable("validationErrors", errorMessages.toString());
    }

    // Utility method to validate contact format
    private boolean isValidContact(String contact) {
        // Simple regex for validating phone numbers (adjust pattern as needed)
        String regex = "^\\+?[0-9]{10,15}$";  // Matches international phone numbers
        return Pattern.matches(regex, contact);
    }
}
