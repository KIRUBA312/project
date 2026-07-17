package com.example.api_monetization.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.api_monetization.entity.DashboardSummary;
@Repository
public interface DashboardSummaryRepository extends JpaRepository<DashboardSummary, Long> {

    Optional<DashboardSummary> findBySummaryDate(LocalDate summaryDate);

    Optional<DashboardSummary>
    findFirstByOrderBySummaryDateDesc();
}