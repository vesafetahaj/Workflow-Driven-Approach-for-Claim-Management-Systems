package com.claims.claims.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("calculateInterest")
public class CalculateInterestDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        System.out.println("Calculating interest...");
        Object loanAmount = execution.getVariable("loanAmount");
        System.out.println("Loan Amount: " + loanAmount);
    }
}