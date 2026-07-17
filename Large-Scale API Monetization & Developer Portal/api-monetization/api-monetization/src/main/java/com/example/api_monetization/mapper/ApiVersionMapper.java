package com.example.api_monetization.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.example.api_monetization.dto.api.ApiVersionRequest;
import com.example.api_monetization.dto.api.ApiVersionResponse;
import com.example.api_monetization.entity.ApiVersion;

@Mapper(config = MapperConfig.class)
public interface ApiVersionMapper {

    @Mapping(target = "api", ignore = true)
    ApiVersion toEntity(ApiVersionRequest dto);

    @Mapping(source = "api.id", target = "apiId")
    ApiVersionResponse toResponse(ApiVersion entity);

    @Mapping(target = "api", ignore = true)
    void updateEntity(ApiVersionRequest dto,
                      @MappingTarget ApiVersion entity);

}