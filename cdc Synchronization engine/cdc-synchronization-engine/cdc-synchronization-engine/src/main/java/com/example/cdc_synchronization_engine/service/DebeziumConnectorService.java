package com.example.cdc_synchronization_engine.service;

import com.example.cdc_synchronization_engine.dto.DebeziumConnectorResponse;

public interface DebeziumConnectorService {

    DebeziumConnectorResponse registerConnector();

    DebeziumConnectorResponse connectorStatus();

    DebeziumConnectorResponse restartConnector();

    DebeziumConnectorResponse deleteConnector();

}