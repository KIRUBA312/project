package com.example.api_monetization.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.example.api_monetization.dto.developer.DeveloperRequest;
import com.example.api_monetization.dto.developer.DeveloperResponse;
import com.example.api_monetization.entity.DeveloperProfile;

@Mapper(config = MapperConfig.class)
public interface DeveloperMapper {
	
	DeveloperProfile toEntity(DeveloperRequest dto);
	DeveloperResponse toResponse(DeveloperProfile entity);
	
	void updateEntity(DeveloperRequest dto,
			@MappingTarget DeveloperProfile entity);

}
