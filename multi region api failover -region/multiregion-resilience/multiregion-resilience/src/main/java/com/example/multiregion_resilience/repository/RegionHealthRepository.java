package com.example.multiregion_resilience.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.multiregion_resilience.entity.RegionHealth;
import com.example.multiregion_resilience.enums.HealthStatus;

@Repository
public interface RegionHealthRepository
        extends JpaRepository<RegionHealth, Long> {

    List<RegionHealth> findByRegionIdOrderByCheckedAtDesc(
            Long regionId
    );

    Optional<RegionHealth>
    findTopByRegionIdOrderByCheckedAtDesc(
            Long regionId
    );

    List<RegionHealth> findByStatus(
            HealthStatus status
    );

    Page<RegionHealth> findByStatus(
            HealthStatus status,
            Pageable pageable
    );

    List<RegionHealth>
    findByRegionIdAndStatus(
            Long regionId,
            HealthStatus status
    );

    Page<RegionHealth>
    findByRegionId(
            Long regionId,
            Pageable pageable
    );
}