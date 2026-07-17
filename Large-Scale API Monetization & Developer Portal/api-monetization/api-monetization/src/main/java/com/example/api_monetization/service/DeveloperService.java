package com.example.api_monetization.service;

import java.util.List;

import com.example.api_monetization.dto.developer.ApiKeyResponse;
import com.example.api_monetization.dto.developer.ConsumerApplicationRequest;
import com.example.api_monetization.dto.developer.ConsumerApplicationResponse;
import com.example.api_monetization.dto.developer.DeveloperRequest;
import com.example.api_monetization.dto.developer.DeveloperResponse;

import jakarta.validation.Valid;

public interface DeveloperService {

	DeveloperResponse createDeveloper(Long userId, @Valid DeveloperRequest request);

	DeveloperResponse updateDeveloper(Long developerId, @Valid DeveloperRequest request);

	DeveloperResponse getDeveloper(Long developerId);

	void deleteDeveloper(Long developerId);

	ConsumerApplicationResponse createApplication(
			Long developerId,
			@Valid ConsumerApplicationRequest request);

	ConsumerApplicationResponse updateApplication(
			Long applicationId,
			@Valid ConsumerApplicationRequest request);

	List<ConsumerApplicationResponse> getApplications(Long developerId);

	ConsumerApplicationResponse getApplication(Long applicationId);

	void deleteApplication(Long applicationId);

	ApiKeyResponse generateApiKey(Long applicationId);

	ApiKeyResponse regenerateApiKey(Long apiKeyId);

	void revokeApiKey(Long apiKeyId);

	List<ApiKeyResponse> getApiKeys(Long applicationId);

	List<DeveloperResponse> getDevelopers();

}
