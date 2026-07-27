package com.example.multiregion_resilience.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.multiregion_resilience.dto.AuditLogResponse;
import com.example.multiregion_resilience.dto.PageResponse;
import com.example.multiregion_resilience.entity.AuditLog;
import com.example.multiregion_resilience.exception.ErrorCode;
import com.example.multiregion_resilience.exception.InvalidOperationException;
import com.example.multiregion_resilience.mapper.AuditLogMapper;
import com.example.multiregion_resilience.repository.AuditLogRepository;
import com.example.multiregion_resilience.service.AuditService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AuditServiceImpl implements AuditService{

	private final AuditLogRepository auditLogRepository;
	private final AuditLogMapper auditLogMapper;

	

	public AuditServiceImpl(AuditLogRepository auditLogRepository, AuditLogMapper auditLogMapper) {
		super();
		this.auditLogRepository = auditLogRepository;
		this.auditLogMapper = auditLogMapper;
	}
	@Override
	public PageResponse<AuditLogResponse> getAuditLogs(
			int page, int size, String requestId, 
			String userId,
			String action, String region, String status) {
		// TODO Auto-generated method stub

        validatePagination( page,size);
        Pageable pageable = PageRequest.of(
                        page,
                        size,
                        Sort.by(Sort.Direction.DESC,
                                "createdAt"));
        Page<AuditLog> auditLogPage;
        if (requestId != null&& !requestId.isBlank()) {
            auditLogPage = auditLogRepository
            		.findByRequestId(requestId.trim(), 
            				pageable);
        }
        else if (userId != null && !userId.isBlank()) {
            auditLogPage = auditLogRepository.findByUserId(
                            userId.trim(), pageable );
        }
        else if (action != null && !action.isBlank()) {

            auditLogPage = auditLogRepository.findByAction(
                            action.trim(), pageable );
        }
        else if (region != null && !region.isBlank()) {
            auditLogPage =auditLogRepository.findByRegion(
                            region.trim(), pageable);
        }
        else if (status != null && !status.isBlank()) {

            auditLogPage =auditLogRepository.findByStatus(
                            status.trim(),pageable );
        }
        else {
            auditLogPage =auditLogRepository
                            .findAllByOrderByCreatedAtDesc(
                                    pageable );
        }
        return new PageResponse<>(auditLogPage
                        .getContent()
                        .stream()
                        .map(this::toResponse)
                        .toList(),

                auditLogPage.getNumber(),
                auditLogPage.getSize(),
                auditLogPage.getTotalElements(),
                auditLogPage.getTotalPages(),
                auditLogPage.isFirst(),
                auditLogPage.isLast()
        );
    }
    private AuditLogResponse toResponse( AuditLog auditLog) 
    {

        AuditLogResponse response = new AuditLogResponse();
        response.setId( auditLog.getId());
        response.setRequestId(auditLog.getRequestId());
        response.setUserId(auditLog.getUserId());
        response.setAction( auditLog.getAction());
        response.setResource( auditLog.getResource());
        response.setRegion(auditLog.getRegion());
        response.setStatus( auditLog.getStatus());
        response.setDetails(auditLog.getDetails());
        response.setCreatedAt(auditLog.getCreatedAt());
        return response;
    }

    private void validatePagination(
            int page,
            int size
    ) {
        if (page < 0) {
            throw new InvalidOperationException(
                    ErrorCode.INVALID_OPERATION,
                    "Page number cannot be negative"
            );
        }
        if (size <= 0|| size > 100) {
            throw new InvalidOperationException(
                    ErrorCode.INVALID_OPERATION,
                    "Page size must be between 1 and 100"
            );
        }
    }
	@Override
	public Page<AuditLogResponse> getAuditLogsByRequestId(
			String requestId, int page, int size) {
		// TODO Auto-generated method stub
		validateValue(requestId,"Request ID cannot be empty");

        Pageable pageable = createPageable(page, size);
        return auditLogRepository
                .findByRequestId( requestId.trim(), pageable)
                .map(auditLogMapper::toResponse);
	}
	@Override
	public Page<AuditLogResponse> getAuditLogsByUserId(
			String userId, int page, int size) {
		// TODO Auto-generated method stub
		 validateValue( userId, "User ID cannot be empty");
	        Pageable pageable = createPageable(page, size);
	        return auditLogRepository.findByUserId(
	                        userId.trim(),pageable)
	                .map(auditLogMapper
	                		::toResponse);
	}
	@Override
	public Page<AuditLogResponse> getAuditLogsByAction(
			String action, int page, int size) {
		validateValue( action,"Action cannot be empty");
        Pageable pageable = createPageable(page, size);
        return auditLogRepository.findByAction(
                        action.trim(), pageable )
                .map(auditLogMapper::toResponse);
	}
	@Override
	public Page<AuditLogResponse> getAuditLogsByRegion(
			String region, int page, int size) {
		 validateValue( region,"Region cannot be empty" );

	        Pageable pageable = createPageable(page, size);
	        return auditLogRepository.findByRegion(
	                        region.trim().toUpperCase(),
	                        pageable)
	                .map(auditLogMapper
	                		::toResponse);
	}
	@Override
	public Page<AuditLogResponse> getAuditLogsByStatus(
			String status, int page, int size) {
		// TODO Auto-generated method stub
		validateValue(
                status,
                "Status cannot be empty"
        );
        Pageable pageable = createPageable(page, size);
        return auditLogRepository
                .findByStatus(
                        status.trim().toUpperCase(),
                        pageable
                )
                .map(auditLogMapper::toResponse);
	}
	private Pageable createPageable(int page,int size) {

        if (page < 0) {
            throw new InvalidOperationException(
                    ErrorCode.INVALID_OPERATION,
                    "Page number cannot be negative");
        }
        if (size <= 0) {
            throw new InvalidOperationException(
                    ErrorCode.INVALID_OPERATION,
                    "Page size must be greater than zero"
            );
        }
        if (size > 100) {
            throw new InvalidOperationException(
                    ErrorCode.INVALID_OPERATION,
                    "Page size cannot exceed 100");
        }
        return PageRequest.of(
                page,
                size,
                Sort.by(
                        Sort.Direction.DESC,
                        "createdAt"));
    }
    private void validateValue( String value,
            String message) {
        if (value == null|| value.isBlank()) {
            throw new InvalidOperationException(
                    ErrorCode.INVALID_OPERATION,
                    message);
        }
    }
}
