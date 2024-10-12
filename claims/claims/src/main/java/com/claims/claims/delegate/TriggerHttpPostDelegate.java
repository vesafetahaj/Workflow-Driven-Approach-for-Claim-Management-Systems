package com.claims.claims.delegate;

import com.claims.claims.dto.AccidentClaimDTO;
import com.claims.claims.models.AccidentClaim;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component("triggerRequest")
public class TriggerHttpPostDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) {
        AccidentClaim accidentClaim = (AccidentClaim) execution.getVariable("accidentClaim");

        // Assuming you have a REST endpoint to save the claim
        String url = "http://localhost:8080/api/claims/save"; // Replace with your actual API endpoint

        RestTemplate restTemplate = new RestTemplate();

        // Send the POST request
        AccidentClaim savedClaim = restTemplate.postForObject(url, accidentClaim, AccidentClaim.class);

        execution.setVariable("savedClaimId", savedClaim.getId());
        // Set a variable to indicate that the claim has been saved
        execution.setVariable("savedClaim", savedClaim);
    }
}