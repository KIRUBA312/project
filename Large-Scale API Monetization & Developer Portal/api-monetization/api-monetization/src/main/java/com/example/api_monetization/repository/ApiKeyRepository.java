package com.example.api_monetization.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.api_monetization.entity.ApiKey;
import com.example.api_monetization.entity.User;
import com.example.api_monetization.enums.ApiKeyStatus;
@Repository
public interface ApiKeyRepository extends JpaRepository<ApiKey, Long> {

    Optional<ApiKey> findByApiKey(String apiKey);

    Optional<ApiKey> findByApiKeyAndStatus(String apiKey, ApiKeyStatus status);

	List<ApiKey> findByApplicationId(Long applicationId);

}