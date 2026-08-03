package com.example.cdc_synchronization_engine.config;

import org.apache.http.HttpHost;

import org.elasticsearch.client.RestClient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import co.elastic.clients.elasticsearch.ElasticsearchClient;

import co.elastic.clients.transport.ElasticsearchTransport;

import co.elastic.clients.transport.rest_client.RestClientTransport;

import co.elastic.clients.json.jackson.JacksonJsonpMapper;

@Configuration
public class ElasticsearchConfig {

    @Value("${spring.elasticsearch.uris}")
    private String elasticsearchUri;

    @Bean(name = "elasticsearchRestClient")
    public RestClient elasticsearchRestClient() {

        return RestClient.builder(

                HttpHost.create(elasticsearchUri)

        ).build();

    }

    @Bean
    public ElasticsearchTransport elasticsearchTransport(
            @org.springframework.beans.factory.annotation.Qualifier("elasticsearchRestClient")
            RestClient restClient) {

        return new RestClientTransport(

                restClient,

                new JacksonJsonpMapper()

        );

    }

    @Bean
    ElasticsearchClient elasticsearchClient(
            ElasticsearchTransport transport) {

        return new ElasticsearchClient(transport);

    }

}