package com.example.cdc_synchronization_engine.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cdc_synchronization_engine.entity.DeadLetterEvent;

@Repository
public interface DeadLetterEventRepository
        extends JpaRepository<DeadLetterEvent, Long> {

    List<DeadLetterEvent>
    findByTopicName(String topicName);
}