package com.example.disasterrecovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class DisasterrecoveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(
				DisasterrecoveryApplication.class, args);
	}

}
