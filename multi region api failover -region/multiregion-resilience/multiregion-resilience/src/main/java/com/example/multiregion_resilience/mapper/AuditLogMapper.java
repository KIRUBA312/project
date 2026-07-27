package com.example.multiregion_resilience.mapper;

import org.springframework.stereotype.Component;

import com.example.multiregion_resilience.dto.AuditLogResponse;
import com.example.multiregion_resilience.entity.AuditLog;

@Component
public class AuditLogMapper {

	public AuditLogResponse toResponse(
            AuditLog auditLog
    ) {

        if (auditLog == null) {
            return null;
        }

        AuditLogResponse response =
                new AuditLogResponse();

        response.setId(
                auditLog.getId()
        );

        response.setRequestId(
                auditLog.getRequestId()
        );

        response.setUserId(
                auditLog.getUserId()
        );

        response.setAction(
                auditLog.getAction()
        );

        response.setResource(
                auditLog.getResource()
        );

        response.setRegion(
                auditLog.getRegion()
        );

        response.setStatus(
                auditLog.getStatus()
        );

        response.setDetails(
                auditLog.getDetails()
        );

        response.setCreatedAt(
                auditLog.getCreatedAt()
        );

        return response;
    }
}
