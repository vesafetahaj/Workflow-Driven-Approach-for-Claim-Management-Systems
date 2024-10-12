package com.claims.claims;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.claims.claims")
public class ClaimsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClaimsApplication.class, args);
	}

}
