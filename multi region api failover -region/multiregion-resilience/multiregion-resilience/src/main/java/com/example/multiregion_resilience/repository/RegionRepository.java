package com.example.multiregion_resilience.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.multiregion_resilience.entity.Region;
import com.example.multiregion_resilience.enums.RegionStatus;

@Repository
public interface RegionRepository
        extends JpaRepository<Region, Long> {

    Optional<Region> findByRegionCode(
            String regionCode
    );

    boolean existsByRegionCode(
            String regionCode
    );

    List<Region> findByStatusAndEnabledTrue(
            RegionStatus status
    );

    List<Region> findByEnabledTrueOrderByPriorityAsc();

    List<Region> findByStatusAndEnabledTrueOrderByPriorityAsc(
            RegionStatus status
    );

    List<Region> findByStatusAndEnabled(
            RegionStatus status,
            Boolean enabled
    );

    Page<Region> findByStatus(
            RegionStatus status,
            Pageable pageable
    );

    Page<Region> findByEnabled(
            Boolean enabled,
            Pageable pageable
    );

    Page<Region> findByStatusAndEnabled(
            RegionStatus status,
            Boolean enabled,
            Pageable pageable
    );
}