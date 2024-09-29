package com.claims.claims.dto;

import lombok.Data;

import java.util.Date;

@Data
public class RegisterDto {
    private String firstName;
    private String lastName;
    private String country;
    private String city;
    private String postal_code;
    private String address;
    private String phone;
    private String email;
    private String password;

}