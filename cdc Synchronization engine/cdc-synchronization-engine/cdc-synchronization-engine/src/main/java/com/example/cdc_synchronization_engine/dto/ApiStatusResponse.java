package com.example.cdc_synchronization_engine.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiStatusResponse {

    private String serviceName;

    private String applicationStatus;

    private String kafkaStatus;

    private String redisStatus;

    private String elasticsearchStatus;

    private String databaseStatus;

    private LocalDateTime serverTime;

}