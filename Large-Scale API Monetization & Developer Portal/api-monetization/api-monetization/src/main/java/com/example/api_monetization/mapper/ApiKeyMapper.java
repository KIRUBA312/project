package com.example.api_monetization.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.example.api_monetization.dto.developer.ApiKeyRequest;
import com.example.api_monetization.dto.developer.ApiKeyResponse;
import com.example.api_monetization.entity.ApiKey;

@Mapper(config = MapperConfig.class)
public interface ApiKeyMapper {
	
	ApiKey toEntity(ApiKeyRequest dto);
	
	ApiKeyResponse toResponse(ApiKey entity);
	
	void updateEntity(ApiKeyRequest dto,@MappingTarget ApiKey entity);

}
