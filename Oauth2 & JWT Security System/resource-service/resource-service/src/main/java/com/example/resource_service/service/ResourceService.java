package com.example.resource_service.service;

import java.util.List;

import com.example.resource_service.dto.ResourceRequestDto;
import com.example.resource_service.dto.ResourceResponseDto;

public interface ResourceService {

	ResourceResponseDto createResource(ResourceRequestDto dto);

	List<ResourceResponseDto> getAllResources();

	ResourceResponseDto getResourceById(Long id);

	ResourceResponseDto updateResource(Long id, ResourceRequestDto dto);

	String deleteResource(Long id);

	List<ResourceResponseDto> getResourcesByTenant(Long tenantId);

}
