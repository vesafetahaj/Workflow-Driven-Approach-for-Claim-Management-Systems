package com.claims.claims.delegate;
import com.claims.claims.dto.AccidentClaimDTO;
import com.claims.claims.models.AccidentClaim;
import com.claims.claims.repository.AccidentClaimRepository;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component("saveClaim")
public class CreateClaimRecordDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) {
        // Retrieve the AccidentClaim from the execution context
        AccidentClaim savedClaim = (AccidentClaim) execution.getVariable("savedClaim");

        // Create DTO to exclude ID
        AccidentClaimDTO accidentClaimDTO = new AccidentClaimDTO(savedClaim);

        // Print the AccidentClaim details without the ID
        System.out.println("Claim Record Processed: " + accidentClaimDTO);
    }

}