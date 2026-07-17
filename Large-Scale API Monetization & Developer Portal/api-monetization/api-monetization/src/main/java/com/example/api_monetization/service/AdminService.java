package com.example.api_monetization.service;

import java.util.List;

import com.example.api_monetization.dto.admin.ApprovePublishRequest;
import com.example.api_monetization.dto.admin.EmailTemplateRequest;
import com.example.api_monetization.dto.admin.EmailTemplateResponse;
import com.example.api_monetization.dto.admin.RejectPublishRequest;
import com.example.api_monetization.dto.admin.SystemSettingRequest;
import com.example.api_monetization.dto.admin.SystemSettingResponse;
import com.example.api_monetization.dto.api.ApiPublishResponse;

public interface AdminService {

   

    List<SystemSettingResponse> getAllSettings();

    SystemSettingResponse updateSetting(
            Long id,
            SystemSettingRequest request);

   

     List<ApiPublishResponse> getPendingPublishRequests();

     ApiPublishResponse approvePublishRequest(
                Long requestId,
                ApprovePublishRequest request);

        ApiPublishResponse rejectPublishRequest(
                Long requestId,
                RejectPublishRequest request);
    
    List<EmailTemplateResponse> getAllTemplates();

    EmailTemplateResponse getTemplate(Long id);

    EmailTemplateResponse updateTemplate(
            Long id,
            EmailTemplateRequest request);

}