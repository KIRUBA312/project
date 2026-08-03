package com.example.cdc_synchronization_engine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CdcSynchronizationEngineApplication {

	public static void main(String[] args) {
		SpringApplication.run(CdcSynchronizationEngineApplication.class, args);
	}

}
