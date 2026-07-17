package com.example.api_monetization.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.example.api_monetization.dto.developer.ConsumerApplicationRequest;
import com.example.api_monetization.dto.developer.ConsumerApplicationResponse;
import com.example.api_monetization.entity.ConsumerApplication;

@Mapper(config = MapperConfig.class)
public interface ConsumerApplicationMapper {

	ConsumerApplication toEntity(ConsumerApplicationRequest dto);
	
	@Mapping(source = "developer.id", target = "developerId")
	ConsumerApplicationResponse toResponse(ConsumerApplication entity);
	
	void updateEntity(ConsumerApplicationRequest dto,
			@MappingTarget ConsumerApplication entity);
}
