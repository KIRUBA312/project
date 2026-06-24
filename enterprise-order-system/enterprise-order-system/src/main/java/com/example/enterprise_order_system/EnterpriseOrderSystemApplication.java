package com.example.enterprise_order_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EnterpriseOrderSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnterpriseOrderSystemApplication.class, args);
	}

}
