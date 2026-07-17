package com.example.api_monetization.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.example.api_monetization.dto.admin.SystemSettingRequest;
import com.example.api_monetization.dto.admin.SystemSettingResponse;
import com.example.api_monetization.entity.SystemSetting;

@Mapper(componentModel = "spring")
public interface SystemSettingMapper {

    @Mapping(target = "updatedBy",
            expression = "java(entity.getUpdatedBy()==null ? null : entity.getUpdatedBy().getId())")
    SystemSettingResponse toResponse(SystemSetting entity);

    void updateEntity(SystemSettingRequest request,
                      @MappingTarget SystemSetting entity);
}