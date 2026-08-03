package com.example.cdc_synchronization_engine.service.impl;

import java.io.StringReader;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.cdc_synchronization_engine.entity.ElasticsearchSyncQueue;
import com.example.cdc_synchronization_engine.repository.ElasticsearchSyncQueueRepository;
import com.example.cdc_synchronization_engine.service.ElasticsearchSyncService;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.IndexRequest;
import co.elastic.clients.json.JsonData;

import jakarta.json.Json;
import jakarta.json.JsonObject;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ElasticsearchSyncServiceImpl
        implements ElasticsearchSyncService {

    private final ElasticsearchClient elasticsearchClient;

    private final ElasticsearchSyncQueueRepository repository;

    @Override
    public void synchronize() {

        List<ElasticsearchSyncQueue> events =
                repository.findBySyncStatus("PENDING");

        if (events.isEmpty()) {

            return;

        }

        for (ElasticsearchSyncQueue event : events) {

            try {

                JsonObject jsonObject =
                        Json.createReader(
                                new StringReader(event.getPayload()))
                                .readObject();

                IndexRequest<JsonData> request =
                        IndexRequest.of(builder -> builder

                                .index(event.getIndexName())

                                .id(String.valueOf(event.getId()))

                                .document(JsonData.of(jsonObject)));

                elasticsearchClient.index(request);

                event.setSyncStatus("SYNCED");

                repository.save(event);

                log.info(
                        "Elasticsearch Sync Completed : {}",
                        event.getId());

            } catch (Exception ex) {

                log.error(
                        "Elasticsearch Sync Failed : {}",
                        event.getId(),
                        ex);

            }

        }

    }

}