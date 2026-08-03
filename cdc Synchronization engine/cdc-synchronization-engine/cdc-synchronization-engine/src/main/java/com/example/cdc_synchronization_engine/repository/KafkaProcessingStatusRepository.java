package com.example.cdc_synchronization_engine.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cdc_synchronization_engine.entity.KafkaProcessingStatus;

@Repository
public interface KafkaProcessingStatusRepository
        extends JpaRepository<KafkaProcessingStatus, Long> {

    Optional<KafkaProcessingStatus>
    findByTopicNameAndPartitionNumberAndOffsetNumber(
            String topicName,
            Integer partitionNumber,
            Long offsetNumber
    );    
    long countByProcessingStatus(String processingStatus);

}