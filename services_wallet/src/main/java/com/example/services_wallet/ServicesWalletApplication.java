package com.example.services_wallet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class ServicesWalletApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicesWalletApplication.class, args);
	}

}
