package com.claims.claims.repository;

import com.claims.claims.models.AccidentClaim;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccidentClaimRepository extends JpaRepository<AccidentClaim, Long> {
    AccidentClaim findByInsurancePolicyNumber(String insurancePolicyNumber);
}
