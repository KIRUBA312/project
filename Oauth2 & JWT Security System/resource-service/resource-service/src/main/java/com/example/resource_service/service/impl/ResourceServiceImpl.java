package com.example.resource_service.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.resource_service.dto.ResourceRequestDto;
import com.example.resource_service.dto.ResourceResponseDto;
import com.example.resource_service.entity.ResourceEntity;
import com.example.resource_service.exception.ResourceNotFoundException;
import com.example.resource_service.repository.ResourceRepository;
import com.example.resource_service.service.ResourceService;

@Service
public class ResourceServiceImpl implements ResourceService{

	@Autowired
	private ResourceRepository resourceRepository;

	@Override
	public ResourceResponseDto createResource(ResourceRequestDto dto) {
		// TODO Auto-generated method stub
		ResourceEntity resource = new ResourceEntity();
		resource.setResourceName(dto.getResourceName());
		resource.setResourceType(dto.getResourceType());
		resource.setDescription(dto.getDescription());
		resource.setTenantId(dto.getTenantId());
		resource.setCreatedAt(LocalDateTime.now());
		resource.setUpdateAt(LocalDateTime.now());
		
		resource = resourceRepository.save(resource);
		
		return maptoresponse(resource);
	}

	@Override
	public List<ResourceResponseDto> getAllResources() {
		// TODO Auto-generated method stub
		return resourceRepository.findAll().stream()
				.map(this::maptoresponse)
				.collect(Collectors.toList());
	}

	@Override
	public ResourceResponseDto getResourceById(Long id) {
		// TODO Auto-generated method stub
		ResourceEntity resource = resourceRepository.findById(id)
				.orElseThrow(() ->new ResourceNotFoundException(
						"Resource Not Found"));
				
		return maptoresponse(resource);
	}

	@Override
	public ResourceResponseDto updateResource(Long id, ResourceRequestDto dto) {
		// TODO Auto-generated method stub
		ResourceEntity resource = resourceRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(
						"Resource Not Found"));
		resource.setResourceName(dto.getResourceName());
		resource.setResourceType(dto.getResourceType());
		resource.setDescription(dto.getDescription());
		resource.setTenantId(dto.getTenantId());
		resource.setUpdateAt(LocalDateTime.now());
		
		resource = resourceRepository.save(resource);
		
		return maptoresponse(resource);
		
		
	}

	@Override
	public String deleteResource(Long id) {
		// TODO Auto-generated method stub
		ResourceEntity resource = resourceRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
		resourceRepository.delete(resource);
		return "Resource Deleted Successfully";
	}

	@Override
	public List<ResourceResponseDto> getResourcesByTenant(Long tenantId) {
		// TODO Auto-generated method stub
		return resourceRepository.findById(tenantId).stream()
				.map(this::maptoresponse)
				.collect(Collectors.toList());
	}
	
	private ResourceResponseDto maptoresponse(ResourceEntity resource) {
		ResourceResponseDto dto = new ResourceResponseDto();
		
		dto.setId(resource.getId());
		dto.setResourceName(resource.getResourceName());
		dto.setResourceType(dto.getResourceType());
		dto.setDescription(resource.getDescription());
		dto.setTenantId(resource.getTenantId());
		dto.setCreatedAt(resource.getCreatedAt());
		dto.setUpdatedAt(resource.getUpdateAt());
		
		return dto;
	}
	
	
	
}
