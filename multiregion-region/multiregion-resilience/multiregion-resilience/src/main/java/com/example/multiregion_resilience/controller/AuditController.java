package com.example.multiregion_resilience.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.multiregion_resilience.dto.AuditLogResponse;
import com.example.multiregion_resilience.dto.PageResponse;
import com.example.multiregion_resilience.service.AuditService;

@RestController
@RequestMapping("/api/audit-log")
public class AuditController {

	private final AuditService auditService;

	public AuditController(AuditService auditService) {
		super();
		this.auditService = auditService;
	}
	
	@GetMapping
	public ResponseEntity<PageResponse<AuditLogResponse>>
	getAuditLogs( @RequestParam(defaultValue = "0")
	int page,
	@RequestParam(defaultValue = "20")int size,
	@RequestParam(required = false)String requestId,
	@RequestParam(required = false)String userId,
	@RequestParam(required = false)String action,
	@RequestParam(required = false)String region,
	@RequestParam(required = false)String status
	){
		PageResponse<AuditLogResponse> response =
				auditService.getAuditLogs(page,size,
						requestId,userId,action,region,status);
		return ResponseEntity.ok(response);
	}
	@GetMapping("/request")
	public ResponseEntity<Page<AuditLogResponse>> getByRequestId(
			@RequestParam
            String requestId,
            @RequestParam( defaultValue = "0")
            int page,
            @RequestParam( defaultValue = "20")
            int size)
	{
		return ResponseEntity.ok(auditService
				.getAuditLogsByRequestId(requestId,page,size));
	}
	@GetMapping("/user")
    public ResponseEntity<Page<AuditLogResponse>>
    getByUserId( @RequestParam String userId,
            @RequestParam( defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) 
	{
        return ResponseEntity.ok(
        		auditService.getAuditLogsByUserId(
                        userId,
                        page,
                        size
                ));
    }
	 @GetMapping("/action")
    public ResponseEntity<Page<AuditLogResponse>>
    getByAction(@RequestParam
            String action,
            @RequestParam( defaultValue = "0" )
            int page,
            @RequestParam( defaultValue = "20")
            int size) 
	 {
        return ResponseEntity.ok(
                auditService.getAuditLogsByAction(
                        action,
                        page,
                        size
                ));
    }
	@GetMapping("/region")
    public ResponseEntity<Page<AuditLogResponse>>
    getByRegion(
            @RequestParam
            String region,

            @RequestParam( defaultValue = "0")
            int page,
            @RequestParam( defaultValue = "20")
            int size) 
	{
        return ResponseEntity.ok(
                auditService.getAuditLogsByRegion(
                        region,
                        page,
                        size
                )
        );
    }

    @GetMapping("/status")
    public ResponseEntity<Page<AuditLogResponse>>
    getByStatus(
            @RequestParam
            String status,
            @RequestParam( defaultValue = "0")
            int page,
            @RequestParam( defaultValue = "20" )
            int size) 
    {
        return ResponseEntity.ok(
                auditService.getAuditLogsByStatus(
                        status,
                        page,
                        size
                )
        );
    }
	 
	
}
