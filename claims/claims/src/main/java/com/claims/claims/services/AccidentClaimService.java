package com.claims.claims.services;

import com.claims.claims.models.AccidentClaim;

public interface AccidentClaimService {
    AccidentClaim saveAccidentClaim(AccidentClaim accidentClaim);
    String submitClaim(AccidentClaim accidentClaim);
}
