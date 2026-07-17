package com.example.api_monetization.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.api_monetization.dto.admin.ApprovePublishRequest;
import com.example.api_monetization.dto.admin.EmailTemplateRequest;
import com.example.api_monetization.dto.admin.EmailTemplateResponse;
import com.example.api_monetization.dto.admin.RejectPublishRequest;
import com.example.api_monetization.dto.admin.SystemSettingRequest;
import com.example.api_monetization.dto.admin.SystemSettingResponse;
import com.example.api_monetization.dto.api.ApiPublishResponse;
import com.example.api_monetization.entity.Api;
import com.example.api_monetization.entity.ApiPublishRequest;
import com.example.api_monetization.entity.EmailTemplate;
import com.example.api_monetization.entity.SystemSetting;
import com.example.api_monetization.entity.User;
import com.example.api_monetization.enums.ApiLifecycleStatus;
import com.example.api_monetization.enums.PublishRequestStatus;
import com.example.api_monetization.exception.ResourceNotFoundException;
import com.example.api_monetization.mapper.ApiPublishRequestMapper;
import com.example.api_monetization.mapper.EmailTemplateMapper;
import com.example.api_monetization.mapper.SystemSettingMapper;
import com.example.api_monetization.repository.ApiPublishRequestRepository;
import com.example.api_monetization.repository.ApiRepository;
import com.example.api_monetization.repository.EmailTemplateRepository;
import com.example.api_monetization.repository.SystemSettingRepository;
import com.example.api_monetization.repository.UserRepository;
import com.example.api_monetization.service.AdminService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminServiceImpl implements AdminService {

    private final SystemSettingRepository systemSettingRepository;
    private final EmailTemplateRepository emailTemplateRepository;
    private final SystemSettingMapper systemSettingMapper;
    private final EmailTemplateMapper emailTemplateMapper;

    private final UserRepository userRepository;
    private final ApiPublishRequestRepository publishRepository;

    private final ApiRepository apiRepository;

    private final ApiPublishRequestMapper publishMapper;
    
    @Override
    @Transactional(readOnly = true)
    public List<SystemSettingResponse> getAllSettings() {

        return systemSettingRepository.findAll()
                .stream()
                .map(systemSettingMapper::toResponse)
                .toList();
    }

    @Override
    public SystemSettingResponse updateSetting(
            Long id,
            SystemSettingRequest request) {

        SystemSetting setting =
                systemSettingRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "System setting not found."));

        systemSettingMapper.updateEntity(request, setting);

        SystemSetting updated =
                systemSettingRepository.save(setting);

        return systemSettingMapper.toResponse(updated);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmailTemplateResponse> getAllTemplates() {

        return emailTemplateRepository.findAll()
                .stream()
                .map(emailTemplateMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public EmailTemplateResponse getTemplate(Long id) {

        EmailTemplate template =
                emailTemplateRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Email template not found."));

        return emailTemplateMapper.toResponse(template);
    }

    @Override
    public EmailTemplateResponse updateTemplate(
            Long id,
            EmailTemplateRequest request) {

        EmailTemplate template =
                emailTemplateRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Email template not found."));

        emailTemplateMapper.updateEntity(request, template);

        EmailTemplate updated =
                emailTemplateRepository.save(template);

        return emailTemplateMapper.toResponse(updated);
    }

	@Override
	public List<ApiPublishResponse> getPendingPublishRequests() {
		// TODO Auto-generated method stub
		return publishRepository
				.findByRequestStatus(PublishRequestStatus.PENDING)
				.stream().map(publishMapper::toResponse)
				.toList();
	}

	@Override
	public ApiPublishResponse approvePublishRequest(Long requestId, ApprovePublishRequest dto) {
		// TODO Auto-generated method stub
		ApiPublishRequest request =
	            publishRepository.findById(requestId)
	            .orElseThrow(() ->
	                    new ResourceNotFoundException(
	                            "Publish Request not found"));
	
		User admin = userRepository.findById(dto.getAdminId())
				.orElseThrow(() ->
				new ResourceNotFoundException(
						"Admin not found"));
		request.setApprovedBy(admin);
	    request.setRequestStatus(PublishRequestStatus.APPROVED);
	
	    request.setApprovalDate(LocalDateTime.now());
	    request.setRejectionReason(dto.getComments());
	
	    Api api = request.getApi();
	
	    api.setLifecycleStatus(ApiLifecycleStatus.PUBLISHED);
	
	    apiRepository.save(api);
	
	    publishRepository.save(request);
	    return publishMapper.toResponse(request);

	}

	@Override
	public ApiPublishResponse rejectPublishRequest(Long requestId, RejectPublishRequest dto) {
		// TODO Auto-generated method stub
		ApiPublishRequest request =
	            publishRepository.findById(requestId)
	            .orElseThrow(() ->
	                    new ResourceNotFoundException(
	                            "Publish Request not found"));
	
		User admin = userRepository.findById(dto.getAdminId())
				.orElseThrow(() ->
				new ResourceNotFoundException(
						"Admin not found"));
		request.setApprovedBy(admin);
	    request.setRequestStatus(PublishRequestStatus.REJECTED);
	
	    request.setApprovalDate(LocalDateTime.now());
	    request.setRejectionReason(dto.getComments());
	
	    Api api = request.getApi();
	
	    api.setLifecycleStatus(ApiLifecycleStatus.REJECTED);
	
	    apiRepository.save(api);
	
	    publishRepository.save(request);
	    return publishMapper.toResponse(request);
	}

}