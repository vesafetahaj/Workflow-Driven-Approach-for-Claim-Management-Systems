package com.claims.claims.delegate;

import com.claims.claims.models.AccidentClaim;
import com.claims.claims.models.enums.ClaimStatus;
import com.claims.claims.services.AccidentClaimService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component("claimSubmission")
public class ClaimSubmissionDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) {
        // Retrieve variables from the process execution context
        String claimantName = (String) execution.getVariable("claimantName");
        String claimantContact = (String) execution.getVariable("claimantContact");
        String accidentDetails = (String) execution.getVariable("accidentDetails");
        String insurancePolicyNumber = (String) execution.getVariable("insurancePolicyNumber");
        Double claimAmount = (Double) execution.getVariable("claimAmount");
        LocalDate accidentDate = (LocalDate) execution.getVariable("accidentDate");

        // Create a new AccidentClaim using the provided data
        AccidentClaim accidentClaim = new AccidentClaim();
        accidentClaim.setClaimantName(claimantName);
        accidentClaim.setClaimantContact(claimantContact);
        accidentClaim.setAccidentDetails(accidentDetails);
        accidentClaim.setAccidentDate(accidentDate);
        accidentClaim.setInsurancePolicyNumber(insurancePolicyNumber);
        accidentClaim.setClaimAmount(claimAmount);
        accidentClaim.setClaimSubmissionDate(LocalDate.now()); // Set current date for submission
        accidentClaim.setClaimStatus(ClaimStatus.PENDING); // Set default claim status

        // Set the AccidentClaim object in the execution context for further processing
        execution.setVariable("accidentClaim", accidentClaim);
    }

}
