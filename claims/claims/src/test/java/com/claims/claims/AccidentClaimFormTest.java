package com.claims.claims;

import com.claims.claims.models.AccidentClaim;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AccidentClaimFormTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testSubmitAccidentClaim() throws Exception {
        String claimantName = "John Doe";
        String claimantContact = "+1234567890";
        String accidentDetails = "Minor accident with no injuries.";
        String insurancePolicyNumber = "POLICY12345";
        Double claimAmount = 1500.00;
        LocalDate accidentDate = LocalDate.now().minusDays(1); // 1 day before today

        AccidentClaim claim = createAccidentClaim(claimantName, claimantContact, accidentDetails, insurancePolicyNumber, claimAmount, accidentDate);

        mockMvc.perform(post("/api/claims/submit") // Change this to your actual endpoint
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(claim)))
                .andExpect(status().isCreated()); // Expecting 201 Created status
    }

    private AccidentClaim createAccidentClaim(String name, String contact, String details, String policyNumber, Double amount, LocalDate date) {
        AccidentClaim claim = new AccidentClaim();
        claim.setClaimantName(name);
        claim.setClaimantContact(contact);
        claim.setAccidentDetails(details);
        claim.setInsurancePolicyNumber(policyNumber);
        claim.setClaimAmount(amount);
        claim.setAccidentDate(date);
        return claim;
    }
}
