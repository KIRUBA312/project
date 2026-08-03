package com.example.cdc_synchronization_engine.dto;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DebeziumConnectorRequest {

    private String name;

    private Map<String, String> config;

}