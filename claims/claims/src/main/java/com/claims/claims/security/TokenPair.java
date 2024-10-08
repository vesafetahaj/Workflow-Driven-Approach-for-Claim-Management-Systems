package com.claims.claims.security;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenPair {
    private String accessToken;
    private String refreshToken;
}