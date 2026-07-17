package com.example.api_monetization.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.example.api_monetization.dto.admin.EmailTemplateRequest;
import com.example.api_monetization.dto.admin.EmailTemplateResponse;
import com.example.api_monetization.entity.EmailTemplate;

@Mapper(config = MapperConfig.class)
public interface EmailTemplateMapper {

    EmailTemplateResponse toResponse(EmailTemplate entity);

    void updateEntity(
            EmailTemplateRequest dto,
            @MappingTarget EmailTemplate entity);

}