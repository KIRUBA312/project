package com.example.api_monetization.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.api_monetization.dto.developer.ApiKeyResponse;
import com.example.api_monetization.dto.developer.ConsumerApplicationRequest;
import com.example.api_monetization.dto.developer.ConsumerApplicationResponse;
import com.example.api_monetization.dto.developer.DeveloperRequest;
import com.example.api_monetization.dto.developer.DeveloperResponse;
import com.example.api_monetization.entity.ApiKey;
import com.example.api_monetization.entity.ConsumerApplication;
import com.example.api_monetization.entity.DeveloperProfile;
import com.example.api_monetization.entity.User;
import com.example.api_monetization.enums.AccountStatus;
import com.example.api_monetization.enums.ApiKeyStatus;
import com.example.api_monetization.exception.ResourceAlreadyExistsException;
import com.example.api_monetization.exception.ResourceNotFoundException;
import com.example.api_monetization.mapper.ApiKeyMapper;
import com.example.api_monetization.mapper.ConsumerApplicationMapper;
import com.example.api_monetization.mapper.DeveloperMapper;
import com.example.api_monetization.repository.ApiKeyRepository;
import com.example.api_monetization.repository.ConsumerApplicationRepository;
import com.example.api_monetization.repository.DeveloperProfileRepository;
import com.example.api_monetization.repository.UserRepository;
import com.example.api_monetization.service.DeveloperService;
import com.example.api_monetization.util.ApiKeyGenerator;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class DeveloperServiceImpl implements DeveloperService{

	@Autowired
	private DeveloperProfileRepository developerProfileRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ConsumerApplicationRepository consumerApplicationRepository;
	@Autowired
	private ApiKeyRepository apiKeyRepository;
	@Autowired
	private DeveloperMapper developerMapper;
	@Autowired
	private ConsumerApplicationMapper consumerApplicationMapper;
	@Override
	public DeveloperResponse createDeveloper(Long userId, 
			@Valid DeveloperRequest request) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(userId).orElseThrow(()->
		new ResourceNotFoundException(
				"User not found with id : "+ userId));
		
		if(developerProfileRepository.existsById(userId)) {
			throw new ResourceAlreadyExistsException(
					"Developer profile already exists.");
		}
		
		DeveloperProfile developer = developerMapper.toEntity(request);
		developer.setUser(user);
		DeveloperProfile saved = developerProfileRepository.save(developer);
				
		return developerMapper.toResponse(saved);
	}
	@Override
	public DeveloperResponse updateDeveloper(Long developerId, 
			@Valid DeveloperRequest request) {
		// TODO Auto-generated method stub
		DeveloperProfile developer =
                developerProfileRepository.findById(developerId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Developer not found"));

        developer.setCompanyName(request.getCompanyName());
        developer.setWebsite(request.getWebsite());
        developer.setAddress(request.getAddress());
        developer.setCountry(request.getCountry());
        developer.setState(request.getState());
        developer.setCity(request.getCity());
        developer.setPostalCode(request.getPostalCode());


        DeveloperProfile updated =
                developerProfileRepository.save(developer);

        return developerMapper.toResponse(updated);
	}
	@Override
	public DeveloperResponse getDeveloper(Long developerId) {
		// TODO Auto-generated method stub
		DeveloperProfile developer =
                developerProfileRepository.findById(developerId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Developer not found with id : "
                                                + developerId));

        return developerMapper.toResponse(developer);
	}
	@Override
	public void deleteDeveloper(Long developerId) {
		// TODO Auto-generated method stub
        DeveloperProfile developer =
                developerProfileRepository.findById(developerId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Developer not found with id : "
                                                + developerId));

        developerProfileRepository.delete(developer);
	}
	@Override
	public ConsumerApplicationResponse createApplication(Long developerId, @Valid ConsumerApplicationRequest request) {
		// TODO Auto-generated method stub
		 DeveloperProfile developer = developerProfileRepository
	                .findById(developerId)
	                .orElseThrow(() -> new ResourceNotFoundException(
	                        "Developer not found with id : " + developerId));

	        ConsumerApplication application =
	                consumerApplicationMapper.toEntity(request);

	        application.setDeveloper(developer);

	        application.setStatus(AccountStatus.ACTIVE);

	        ConsumerApplication saved =
	                consumerApplicationRepository.save(application);

	        return consumerApplicationMapper.toResponse(saved);
	}
	@Override
	public ConsumerApplicationResponse updateApplication(Long applicationId,
			@Valid ConsumerApplicationRequest request) {
		// TODO Auto-generated method stub
		ConsumerApplication application =
                consumerApplicationRepository.findById(applicationId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Application not found with id : " + applicationId));

        application.setApplicationName(request.getApplicationName());

        application.setDescription(request.getDescription());

        application.setRedirectUrl(request.getRedirectUrl());

        application.setCallbackUrl(request.getCallbackUrl());

        ConsumerApplication updated =
                consumerApplicationRepository.save(application);

        return consumerApplicationMapper.toResponse(updated);
	}
	@Override
	public List<ConsumerApplicationResponse> getApplications(Long developerId) {

        if (!developerProfileRepository.existsById(developerId)) {

            throw new ResourceNotFoundException(
                    "Developer not found with id : " + developerId);
        }

        return consumerApplicationRepository
                .findByDeveloperId(developerId)
                .stream()
                .map(consumerApplicationMapper::toResponse)
                .toList();
	}
	@Override
	public ConsumerApplicationResponse getApplication(Long applicationId) {
		ConsumerApplication application =
                consumerApplicationRepository.findById(applicationId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Application not found with id : " + applicationId));

        return consumerApplicationMapper.toResponse(application);
	}
	@Override
	public void deleteApplication(Long applicationId) {
        ConsumerApplication application =
                consumerApplicationRepository.findById(applicationId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Application not found with id : " + applicationId));

        consumerApplicationRepository.delete(application);
		
	}
	@Autowired
	private ApiKeyMapper apiKeyMapper;
	@Autowired
	private ApiKeyGenerator apiKeyGenerator;
	@Override
	public ApiKeyResponse generateApiKey(Long applicationId) {
		// TODO Auto-generated method stub
		ConsumerApplication application =
                consumerApplicationRepository.findById(applicationId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Application not found with id : " + applicationId));

        ApiKey apiKey = new ApiKey();

        apiKey.setApplication(application);

        apiKey.setApiKey(apiKeyGenerator.generateApiKey());

        apiKey.setApiSecret(apiKeyGenerator.generateApiSecret());

        apiKey.setStatus(ApiKeyStatus.ACTIVE);

        ApiKey saved = apiKeyRepository.save(apiKey);

        return apiKeyMapper.toResponse(saved);
	}
	@Override
	public ApiKeyResponse regenerateApiKey(Long apiKeyId) {
		// TODO Auto-generated method stub
		ApiKey apiKey = apiKeyRepository.findById(apiKeyId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "API Key not found with id : " + apiKeyId));

        apiKey.setApiKey(apiKeyGenerator.generateApiKey());

        apiKey.setApiSecret(apiKeyGenerator.generateApiSecret());

        apiKey.setRegeneratedAt(LocalDateTime.now());

        ApiKey updated = apiKeyRepository.save(apiKey);

        return apiKeyMapper.toResponse(updated);
    
	}
	@Override
	public void revokeApiKey(Long apiKeyId) {
		// TODO Auto-generated method stub
		ApiKey apiKey = apiKeyRepository.findById(apiKeyId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "API Key not found with id : " + apiKeyId));

        apiKey.setStatus(ApiKeyStatus.REVOKED);

        apiKeyRepository.save(apiKey);
		
	}
	@Override
	public List<ApiKeyResponse> getApiKeys(Long applicationId) {
		// TODO Auto-generated method stub
		if (!consumerApplicationRepository.existsById(applicationId)) {

            throw new ResourceNotFoundException(
                    "Application not found with id : " + applicationId);
        }

        return apiKeyRepository.findByApplicationId(applicationId)
                .stream()
                .map(apiKeyMapper::toResponse)
                .toList();
	}
	@Override
	public List<DeveloperResponse> getDevelopers() {
		// TODO Auto-generated method stub
		return developerProfileRepository.findAll().stream()
				.map(developerMapper::toResponse)
				.collect(Collectors.toList());
	}
	
	
	
}
