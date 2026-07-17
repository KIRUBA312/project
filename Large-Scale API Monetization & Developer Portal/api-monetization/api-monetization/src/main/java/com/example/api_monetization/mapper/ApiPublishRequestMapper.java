package com.example.api_monetization.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.example.api_monetization.dto.api.ApiPublishRequest;
import com.example.api_monetization.dto.api.ApiPublishResponse;


@Mapper(config = MapperConfig.class)
public interface ApiPublishRequestMapper {

    @Mapping(target = "api", ignore = true)
    @Mapping(target = "requestedBy", ignore = true)
    @Mapping(target = "approvedBy", ignore = true)
    @Mapping(target = "requestStatus", ignore = true)
    com.example.api_monetization.entity.ApiPublishRequest toEntity(ApiPublishRequest dto);

    @Mapping(source = "api.id", target = "apiId")
    @Mapping(source = "requestedBy.id", target = "requestedBy")
    @Mapping(source = "approvedBy.id", target = "approvedBy")
    ApiPublishResponse toResponse(com.example.api_monetization.entity.ApiPublishRequest entity);

    @Mapping(target = "api", ignore = true)
    @Mapping(target = "requestedBy", ignore = true)
    @Mapping(target = "approvedBy", ignore = true)
    @Mapping(target = "requestStatus", ignore = true)
    void updateEntity(ApiPublishRequest dto,
                      @MappingTarget com.example.api_monetization.entity.ApiPublishRequest entity);

}