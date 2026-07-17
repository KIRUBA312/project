package com.example.api_monetization.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.api_monetization.entity.ApiPublishRequest;
import com.example.api_monetization.enums.PublishRequestStatus;
@Repository
public interface ApiPublishRequestRepository extends JpaRepository<ApiPublishRequest, Long> {

    List<ApiPublishRequest> findByRequestStatus(PublishRequestStatus status);

    List<ApiPublishRequest> findByApiId(Long apiId);
}