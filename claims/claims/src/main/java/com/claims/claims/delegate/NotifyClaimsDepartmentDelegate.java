package com.claims.claims.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component("notifyDepartment")
public class NotifyClaimsDepartmentDelegate implements JavaDelegate {

    @Autowired
    private JavaMailSender emailSender;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        Long savedClaimId = (Long) execution.getVariable("savedClaimId");

//        // Notify the department (e.g., send an email, send a message, etc.)
//        sendEmail(savedClaimId);

        System.out.println("Claim ID " + savedClaimId + " has been submitted and needs processing.");
    }

//    private void sendEmail(Long claimId) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo("vesafetahaj2@gmail.com"); // Replace with the actual department email
//        message.setSubject("New Claim Submitted");
//        message.setText("Claim ID " + claimId + " has been submitted and needs processing.");
//
//        emailSender.send(message);
//    }
}