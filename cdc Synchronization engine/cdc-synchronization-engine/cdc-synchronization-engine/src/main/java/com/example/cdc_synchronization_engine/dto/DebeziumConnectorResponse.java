package com.example.cdc_synchronization_engine.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DebeziumConnectorResponse {

    private String connectorName;

    private String status;

    private String message;

}