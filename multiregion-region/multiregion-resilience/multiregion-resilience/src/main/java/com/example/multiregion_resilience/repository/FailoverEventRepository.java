package com.example.multiregion_resilience.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.multiregion_resilience.entity.FailoverEvent;
import com.example.multiregion_resilience.enums.FailoverType;

@Repository
public interface FailoverEventRepository extends 
JpaRepository<FailoverEvent, Long>{

	List<FailoverEvent> findBySourceRegion(
            String sourceRegion
    );

    Page<FailoverEvent> findByTargetRegion(
            String targetRegion, PageRequest pageable
    );

    List<FailoverEvent> findByFailoverType(
            FailoverType failoverType
    );

    Page<FailoverEvent> findBySourceRegion(
            String sourceRegion,
            Pageable pageable
    );

    Page<FailoverEvent> findByFailoverType(
            FailoverType failoverType,
            Pageable pageable
    );

    Page<FailoverEvent> findAllByOrderByCreatedAtDesc(
            Pageable pageable
    );
	
}
