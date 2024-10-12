package com.claims.claims.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("notifyDepartment")
public class NotifyClaimsDepartmentDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        Long savedClaimId = (Long) execution.getVariable("savedClaimId");

        // Notify the department (e.g., send an email, send a message, etc.)
        System.out.println("Claim ID " + savedClaimId + " has been submitted and needs processing.");

        // Implement actual notification logic here

    }
}