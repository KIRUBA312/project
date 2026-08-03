package com.example.cdc_synchronization_engine.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class DebeziumConnectorConfig {

	@Bean(name = "debeziumRestClient")
	public RestClient debeziumRestClient() {

        return RestClient.builder().build();

    }

}