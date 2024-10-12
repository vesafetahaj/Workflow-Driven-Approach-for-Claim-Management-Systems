package com.claims.claims.controller;

import com.claims.claims.models.AccidentClaim;
import com.claims.claims.services.AccidentClaimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
//@RequestMapping("/api/accident-claims")
//public class AccidentClaimController {
//
//    @Autowired
//    private AccidentClaimService accidentClaimService;
//
//    @PostMapping
//    public AccidentClaim submitAccidentClaim(@RequestBody AccidentClaim accidentClaim) {
//        return accidentClaimService.createAccidentClaim(accidentClaim);
//    }
//}