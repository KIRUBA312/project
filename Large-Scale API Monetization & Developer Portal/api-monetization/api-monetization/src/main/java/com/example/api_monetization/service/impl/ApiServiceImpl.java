package com.example.api_monetization.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.api_monetization.dto.api.ApiCategoryRequest;
import com.example.api_monetization.dto.api.ApiCategoryResponse;
import com.example.api_monetization.dto.api.ApiDocumentationRequest;
import com.example.api_monetization.dto.api.ApiDocumentationResponse;
import com.example.api_monetization.dto.api.ApiPublishRequest;
import com.example.api_monetization.dto.api.ApiPublishResponse;
import com.example.api_monetization.dto.api.ApiRequest;
import com.example.api_monetization.dto.api.ApiResponse;
import com.example.api_monetization.dto.api.ApiVersionRequest;
import com.example.api_monetization.dto.api.ApiVersionResponse;
import com.example.api_monetization.entity.Api;
import com.example.api_monetization.entity.ApiCategory;
import com.example.api_monetization.entity.ApiDocumentation;
import com.example.api_monetization.entity.ApiVersion;
import com.example.api_monetization.entity.User;
import com.example.api_monetization.enums.ApiLifecycleStatus;
import com.example.api_monetization.enums.ApiVisibility;
import com.example.api_monetization.enums.AuthenticationType;
import com.example.api_monetization.enums.PublishRequestStatus;
import com.example.api_monetization.exception.ResourceAlreadyExistsException;
import com.example.api_monetization.exception.ResourceNotFoundException;
import com.example.api_monetization.mapper.ApiCategoryMapper;
import com.example.api_monetization.mapper.ApiDocumentationMapper;
import com.example.api_monetization.mapper.ApiMapper;
import com.example.api_monetization.mapper.ApiPublishRequestMapper;
import com.example.api_monetization.mapper.ApiVersionMapper;
import com.example.api_monetization.repository.ApiCategoryRepository;
import com.example.api_monetization.repository.ApiDocumentationRepository;
import com.example.api_monetization.repository.ApiPublishRequestRepository;
import com.example.api_monetization.repository.ApiRepository;
import com.example.api_monetization.repository.ApiVersionRepository;
import com.example.api_monetization.repository.UserRepository;
import com.example.api_monetization.service.ApiService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ApiServiceImpl implements ApiService{

	
	private final ApiRepository apiRepository;
	
	private final ApiCategoryRepository apiCategoryRepository;
	
	private final ApiVersionRepository apiVersionRepository;
	
	private final ApiDocumentationRepository apiDocumentationRepository;
	
	private final ApiPublishRequestRepository apiPublishRequestRepository;
	
	private final ApiMapper apiMapper;
	
	private final ApiCategoryMapper apiCategoryMapper;
	
	private final UserRepository userRepository;
	
	private final ApiVersionMapper apiVersionMapper;

	private final ApiPublishRequestMapper apiPublishRequestMapper;
	
	@Override
	public ApiCategoryResponse createCategory(ApiCategoryRequest request) {
		// TODO Auto-generated method stub
        if (apiCategoryRepository.existsByCategoryNameIgnoreCase(
                request.getCategoryName())) {

            throw new ResourceAlreadyExistsException(
                    "Category already exists.");
        }

        ApiCategory category = apiCategoryMapper.toEntity(request);

        ApiCategory saved = apiCategoryRepository.save(category);

        return apiCategoryMapper.toResponse(saved);
	}
	@Override
	public List<ApiCategoryResponse> getAllCategories() {
		// TODO Auto-generated method stub
		return apiCategoryRepository.findAll().stream()
				.map(apiCategoryMapper::toResponse)
				.collect(Collectors.toList());
	}
	@Override
	public ApiResponse createApi(Long publisherId, ApiRequest request) {
		// TODO Auto-generated method stub
		User publisher = userRepository.findById(publisherId)
				.orElseThrow(() ->
				new ResourceNotFoundException("Publisher not found"));
		Api api = apiMapper.toEntity(request);
		api.setPublisher(publisher);
		if(request.getCategoryId()!=null) {
			ApiCategory category=
					apiCategoryRepository.findById(request.getCategoryId())
					.orElseThrow(()->
					new ResourceNotFoundException("Category not found"));
			api.setCategory(category);
		}
		api.setVisibility(ApiVisibility.PUBLIC);

        api.setLifecycleStatus(ApiLifecycleStatus.DRAFT);

        api.setAuthenticationType(AuthenticationType.API_KEY);

        Api saved = apiRepository.save(api);

        return apiMapper.toResponse(saved);
	}
	@Override
	public ApiResponse updateApi(Long apiId, ApiRequest request) {
		// TODO Auto-generated method stub
		Api api = apiRepository.findById(apiId).orElseThrow(() ->
		new ResourceNotFoundException("Api not found"));
		if(request.getCategoryId()!=null) {
			ApiCategory category = apiCategoryRepository.findById(request.getCategoryId())
					.orElseThrow(()->
					new ResourceNotFoundException("Category not found"));
			api.setCategory(category);
		
		}
		Api updated = apiRepository.save(api);
		return apiMapper.toResponse(updated);
	}
	@Override
	public ApiResponse getApi(Long apiId) {
		// TODO Auto-generated method stub
		Api api = apiRepository.findById(apiId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "API not found."));

        return apiMapper.toResponse(api);
	}
	@Override
	public List<ApiResponse> getAllApis() {
		// TODO Auto-generated method stub
		 return apiRepository.findAll()
	                .stream()
	                .map(apiMapper::toResponse)
	                .collect(Collectors.toList());
	}
	@Override
	public void deleteApi(Long apiId) {
		// TODO Auto-generated method stub
		 Api api = apiRepository.findById(apiId)
	                .orElseThrow(() ->
	                        new ResourceNotFoundException(
	                                "API not found."));

	        apiRepository.delete(api);
	}
	@Override
	public ApiVersionResponse createVersion(Long apiId, ApiVersionRequest request) {
		// TODO Auto-generated method stub
		Api api = apiRepository.findById(apiId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "API not found with id : " + apiId));

        ApiVersion version = apiVersionMapper.toEntity(request);

        version.setApi(api);

        ApiVersion saved =
                apiVersionRepository.save(version);

        return apiVersionMapper.toResponse(saved);
	}
	@Override
	public List<ApiVersionResponse> getVersions(Long apiId) {

        if (!apiRepository.existsById(apiId)) {

            throw new ResourceNotFoundException(
                    "API not found with id : " + apiId);
        }

        return apiVersionRepository.findByApiId(apiId)
                .stream()
                .map(apiVersionMapper::toResponse)
                .toList();
	}
	@Autowired
	private ApiDocumentationMapper apiDocumentationMapper;
	@Override
	public ApiDocumentationResponse createDocumentation(Long apiId, ApiDocumentationRequest request) {
		// TODO Auto-generated method stub
		 Api api = apiRepository.findById(apiId)
	                .orElseThrow(() ->
	                        new ResourceNotFoundException(
	                                "API not found with id : " + apiId));

	        ApiDocumentation documentation;

	        if (apiDocumentationRepository.findByApiId(apiId).isPresent()) {

	            documentation = apiDocumentationRepository
	                    .findByApiId(apiId)
	                    .get();

	            apiDocumentationMapper.updateEntity(request, documentation);

	        } else {

	            documentation = apiDocumentationMapper.toEntity(request);

	            documentation.setApi(api);
	        }

	        ApiDocumentation saved =
	                apiDocumentationRepository.save(documentation);

	        return apiDocumentationMapper.toResponse(saved);
	}
	@Override
	public ApiDocumentationResponse getDocumentation(Long apiId) {
		// TODO Auto-generated method stub
		ApiDocumentation documentation =
                apiDocumentationRepository.findByApiId(apiId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Documentation not found for API : "
                                        + apiId));

        return apiDocumentationMapper.toResponse(documentation);
	}
    
    // API PUBLISH REQUEST
    

    @Override
    public ApiPublishResponse publishApi(
            Long apiId,
            ApiPublishRequest request) {

        Api api = apiRepository.findById(apiId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "API not found with id : " + apiId));

        User requestedBy = userRepository.findById(request.getRequestedBy())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Requested User not found."));

        com.example.api_monetization.entity.ApiPublishRequest publishRequest = 
        		new com.example.api_monetization.entity.ApiPublishRequest();

        publishRequest.setApi(api);

        publishRequest.setRequestedBy(requestedBy);

        publishRequest.setRequestStatus(PublishRequestStatus.PENDING);
        publishRequest.setRequestDate(LocalDateTime.now());

        com.example.api_monetization.entity.ApiPublishRequest saved =
                apiPublishRequestRepository.save(publishRequest);

        return apiPublishRequestMapper.toResponse(saved);
    }


	
}
