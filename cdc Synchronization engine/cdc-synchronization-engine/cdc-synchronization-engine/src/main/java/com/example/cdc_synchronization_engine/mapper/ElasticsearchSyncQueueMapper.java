package com.example.cdc_synchronization_engine.mapper;

import org.springframework.stereotype.Component;

import com.example.cdc_synchronization_engine.dto.ElasticsearchSyncQueueResponse;
import com.example.cdc_synchronization_engine.entity.ElasticsearchSyncQueue;

@Component
public class ElasticsearchSyncQueueMapper {

    public ElasticsearchSyncQueueResponse toResponse(ElasticsearchSyncQueue entity){

        ElasticsearchSyncQueueResponse dto=new ElasticsearchSyncQueueResponse();

        dto.setId(entity.getId());
        dto.setIndexName(entity.getIndexName());
        dto.setDocumentId(entity.getDocumentId());
        dto.setPayload(entity.getPayload());
        dto.setSyncStatus(entity.getSyncStatus());
        dto.setCreatedAt(entity.getCreatedAt());

        return dto;
    }
}
