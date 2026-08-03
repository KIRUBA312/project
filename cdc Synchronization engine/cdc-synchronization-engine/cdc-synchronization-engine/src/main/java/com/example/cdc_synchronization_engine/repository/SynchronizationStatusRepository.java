package com.example.cdc_synchronization_engine.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cdc_synchronization_engine.entity.SynchronizationStatus;

@Repository
public interface SynchronizationStatusRepository
        extends JpaRepository<SynchronizationStatus, Long> {

    Optional<SynchronizationStatus>
    findByEntityNameAndEntityId(
            String entityName,
            Long entityId
    );
    long countByStatus(String status);

}