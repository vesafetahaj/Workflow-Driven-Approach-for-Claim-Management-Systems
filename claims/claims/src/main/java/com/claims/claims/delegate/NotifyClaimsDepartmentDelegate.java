package com.claims.claims.delegate;

import com.claims.claims.models.AccidentClaim;
import com.claims.claims.repository.AccidentClaimRepository;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

@Component("notifyDepartment")
public class NotifyClaimsDepartmentDelegate implements JavaDelegate {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private AccidentClaimRepository accidentClaimRepository;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        Long savedClaimId = (Long) execution.getVariable("savedClaimId");

        // Fetch the AccidentClaim data from the repository
        AccidentClaim claim = accidentClaimRepository.findById(savedClaimId)
                .orElseThrow(() -> new Exception("Claim not found with ID: " + savedClaimId));

        // Notify the department (e.g., send an email)
        sendEmail(claim);

    }
    private void sendEmail(AccidentClaim claim) {
        String toEmail = "vesafetahaj2@gmail.com"; // Set the recipient email
        String subject = "New Claim Submitted: ID " + claim.getId();

        // Construct the email body with claim details in a table format
        String body = "<html>" +
                "<body>" +
                "<p>A new claim has been submitted and is awaiting processing:</p>" +
                "<table style='border-collapse: collapse; width: 100%;'>" +
                "<tr style='background-color: #f2f2f2;'>" +
                "<th style='border: 1px solid #dddddd; text-align: left; padding: 8px;'>Claim ID</th>" +
                "<th style='border: 1px solid #dddddd; text-align: left; padding: 8px;'>Claimant Name</th>" +
                "<th style='border: 1px solid #dddddd; text-align: left; padding: 8px;'>Claimant Contact</th>" +
                "<th style='border: 1px solid #dddddd; text-align: left; padding: 8px;'>Accident Details</th>" +
                "<th style='border: 1px solid #dddddd; text-align: left; padding: 8px;'>Date of Accident</th>" +
                "<th style='border: 1px solid #dddddd; text-align: left; padding: 8px;'>Date of Claim Submission</th>" +
                "<th style='border: 1px solid #dddddd; text-align: left; padding: 8px;'>Insurance Policy Number</th>" +
                "<th style='border: 1px solid #dddddd; text-align: left; padding: 8px;'>Claim Amount</th>" +
                "<th style='border: 1px solid #dddddd; text-align: left; padding: 8px;'>Claim Status</th>" +
                "</tr>" +
                "<tr>" +
                "<td style='border: 1px solid #dddddd; padding: 8px;'>" + claim.getId() + "</td>" +
                "<td style='border: 1px solid #dddddd; padding: 8px;'>" + claim.getClaimantName() + "</td>" +
                "<td style='border: 1px solid #dddddd; padding: 8px;'>" + claim.getClaimantContact() + "</td>" +
                "<td style='border: 1px solid #dddddd; padding: 8px;'>" + claim.getAccidentDetails() + "</td>" +
                "<td style='border: 1px solid #dddddd; padding: 8px;'>" + claim.getAccidentDate() + "</td>" +
                "<td style='border: 1px solid #dddddd; padding: 8px;'>" + claim.getClaimSubmissionDate() + "</td>" +
                "<td style='border: 1px solid #dddddd; padding: 8px;'>" + claim.getInsurancePolicyNumber() + "</td>" +
                "<td style='border: 1px solid #dddddd; padding: 8px;'>" + claim.getClaimAmount() + "</td>" +
                "<td style='border: 1px solid #dddddd; padding: 8px;'>" + claim.getClaimStatus() + "</td>" +
                "</tr>" +
                "</table>" +
                "<p>Please review the claim as soon as possible.</p>" +
                "</body>" +
                "</html>";

        // Create a MimeMessage object
        MimeMessage message = emailSender.createMimeMessage();

        try {
            // Use MimeMessageHelper to set the email attributes
            MimeMessageHelper helper = new MimeMessageHelper(message, true); // true indicates multipart message
            helper.setTo(toEmail); // Set the recipient email
            helper.setSubject(subject); // Set the email subject
            helper.setText(body, true); // Set the email body and indicate it is HTML

            // Send the email
            emailSender.send(message);
            System.out.println("Notification email sent to claims department for claim ID " + claim.getId());
        } catch (Exception e) {
            System.out.println("Failed to send email: " + e.getMessage());
        }
    }


}
