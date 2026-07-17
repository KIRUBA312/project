package com.example.api_monetization.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.example.api_monetization.dto.api.ApiRequest;
import com.example.api_monetization.dto.api.ApiResponse;
import com.example.api_monetization.entity.Api;

@Mapper(config = MapperConfig.class)
public interface ApiMapper {
	
	Api toEntity(ApiRequest dto);
	ApiResponse toResponse(Api entity);
	
	void updateEntity(ApiRequest dto,@MappingTarget Api entity);
	

}
