package com.example.api_monetization.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.example.api_monetization.dto.api.ApiDocumentationRequest;
import com.example.api_monetization.dto.api.ApiDocumentationResponse;
import com.example.api_monetization.entity.ApiDocumentation;

@Mapper(config = MapperConfig.class)
public interface ApiDocumentationMapper {

    @Mapping(target = "api", ignore = true)
    ApiDocumentation toEntity(ApiDocumentationRequest dto);

    @Mapping(source = "api.id", target = "apiId")
    ApiDocumentationResponse toResponse(ApiDocumentation entity);

    @Mapping(target = "api", ignore = true)
    void updateEntity(ApiDocumentationRequest dto,
                      @MappingTarget ApiDocumentation entity);

}