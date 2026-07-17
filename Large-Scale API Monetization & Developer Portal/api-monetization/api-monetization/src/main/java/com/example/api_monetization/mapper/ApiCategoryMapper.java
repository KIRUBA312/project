package com.example.api_monetization.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.example.api_monetization.dto.api.ApiCategoryRequest;
import com.example.api_monetization.dto.api.ApiCategoryResponse;
import com.example.api_monetization.entity.ApiCategory;

@Mapper(config = MapperConfig.class)
public interface ApiCategoryMapper {

    ApiCategory toEntity(ApiCategoryRequest dto);

    ApiCategoryResponse toResponse(ApiCategory entity);

    void updateEntity(ApiCategoryRequest dto,
                      @MappingTarget ApiCategory entity);

}