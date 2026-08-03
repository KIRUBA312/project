package com.example.cdc_synchronization_engine.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.example.cdc_synchronization_engine.dto.DebeziumConnectorResponse;
import com.example.cdc_synchronization_engine.service.DebeziumConnectorService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DebeziumConnectorServiceImpl
        implements DebeziumConnectorService {

    private final RestClient restClient;

    @Value("${cdc.debezium.url}")
    private String connectUrl;

    @Value("${cdc.debezium.connector}")
    private String connector;

    @Value("${cdc.debezium.hostname}")
    private String hostname;

    @Value("${cdc.debezium.port}")
    private String port;

    @Value("${cdc.debezium.database}")
    private String database;

    @Value("${cdc.debezium.username}")
    private String username;

    @Value("${cdc.debezium.password}")
    private String password;

    @Value("${cdc.debezium.server-name}")
    private String serverName;

    @Override
    public DebeziumConnectorResponse registerConnector() {

        Map<String, Object> body = new HashMap<>();

        body.put("name", connector);

        Map<String, String> config = new HashMap<>();

        config.put("connector.class",
                "io.debezium.connector.postgresql.PostgresConnector");

        config.put("database.hostname", hostname);
        config.put("database.port", port);
        config.put("database.user", username);
        config.put("database.password", password);
        config.put("database.dbname", database);

        config.put("topic.prefix", serverName);

        config.put("plugin.name", "pgoutput");

        config.put("schema.include.list", "public");

        config.put("table.include.list", "public.*");

        config.put("slot.name", "cdc_slot");

        config.put("publication.autocreate.mode", "filtered");

        body.put("config", config);

        restClient.post()
                .uri(connectUrl + "/connectors")
                .contentType(MediaType.APPLICATION_JSON)
                .body(body)
                .retrieve()
                .toBodilessEntity();

        DebeziumConnectorResponse response =
                new DebeziumConnectorResponse();

        response.setConnectorName(connector);
        response.setStatus("REGISTERED");
        response.setMessage("Connector registered successfully");

        return response;
    }

    @Override
    public DebeziumConnectorResponse connectorStatus() {

        String status =
                restClient.get()
                        .uri(connectUrl + "/connectors/" + connector + "/status")
                        .retrieve()
                        .body(String.class);

        DebeziumConnectorResponse response =
                new DebeziumConnectorResponse();

        response.setConnectorName(connector);
        response.setStatus("RUNNING");
        response.setMessage(status);

        return response;
    }

    @Override
    public DebeziumConnectorResponse restartConnector() {

        restClient.post()
                .uri(connectUrl + "/connectors/" + connector + "/restart")
                .retrieve()
                .toBodilessEntity();

        DebeziumConnectorResponse response =
                new DebeziumConnectorResponse();

        response.setConnectorName(connector);
        response.setStatus("RESTARTED");
        response.setMessage("Connector restarted");

        return response;
    }

    @Override
    public DebeziumConnectorResponse deleteConnector() {

        restClient.delete()
                .uri(connectUrl + "/connectors/" + connector)
                .retrieve()
                .toBodilessEntity();

        DebeziumConnectorResponse response =
                new DebeziumConnectorResponse();

        response.setConnectorName(connector);
        response.setStatus("DELETED");
        response.setMessage("Connector deleted");

        return response;
    }
}