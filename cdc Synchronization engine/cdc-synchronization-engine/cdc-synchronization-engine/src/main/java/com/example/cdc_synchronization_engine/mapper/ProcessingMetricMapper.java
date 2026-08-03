package com.example.cdc_synchronization_engine.mapper;

import org.springframework.stereotype.Component;

import com.example.cdc_synchronization_engine.dto.ProcessingMetricResponse;
import com.example.cdc_synchronization_engine.entity.ProcessingMetric;

@Component
public class ProcessingMetricMapper {

    public ProcessingMetricResponse toResponse(ProcessingMetric entity){

        ProcessingMetricResponse dto=new ProcessingMetricResponse();

        dto.setId(entity.getId());
        dto.setTopicName(entity.getTopicName());
        dto.setProcessedRecords(entity.getProcessedRecords());
        dto.setFailedRecords(entity.getFailedRecords());
        dto.setProcessingTimeMs(entity.getProcessingTimeMs());
        dto.setRecordedAt(entity.getRecordedAt());

        return dto;
    }
}
