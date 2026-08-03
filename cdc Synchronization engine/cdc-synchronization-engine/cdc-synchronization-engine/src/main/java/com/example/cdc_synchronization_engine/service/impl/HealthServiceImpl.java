package com.example.cdc_synchronization_engine.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.cdc_synchronization_engine.dto.ApiStatusResponse;
import com.example.cdc_synchronization_engine.service.HealthService;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HealthServiceImpl implements HealthService {

    private final JdbcTemplate jdbcTemplate;

    private final RedisConnectionFactory redisConnectionFactory;

    private final ElasticsearchClient elasticsearchClient;

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${spring.kafka.bootstrap-servers}")
    private String kafkaServer;

    @Override
    public ApiStatusResponse getApplicationHealth() {

        ApiStatusResponse response =
                new ApiStatusResponse();

        response.setServiceName(applicationName);
        response.setApplicationStatus("UP");
        response.setDatabaseStatus(checkDatabase());
        response.setRedisStatus( checkRedis());
        response.setKafkaStatus( checkKafka());
        response.setElasticsearchStatus( checkElastic());
        response.setServerTime( LocalDateTime.now());

        return response;
    }

    public String checkDatabase() {

        try {

            jdbcTemplate.execute("SELECT 1");

            return "UP";

        } catch (Exception ex) {

            return "DOWN";
        }
    }

    public String checkRedis() {

        try {

            String result =
                    redisConnectionFactory
                            .getConnection()
                            .ping();

            return result != null ? "UP" : "DOWN";

        } catch (Exception ex) {

            return "DOWN";
        }
    }

    public String checkKafka() {

        try {

            java.net.Socket socket =
                    new java.net.Socket();

            socket.connect(
                    new java.net.InetSocketAddress(
                            "localhost",
                            9092),
                    2000);

            socket.close();

            return "UP";

        } catch (Exception ex) {

            return "DOWN";
        }
    }

    public String checkElastic() {

        try {

            boolean status =
                    elasticsearchClient.ping()
                            .value();

            return status ? "UP" : "DOWN";

        } catch (Exception ex) {

            return "DOWN";
        }
    }

}