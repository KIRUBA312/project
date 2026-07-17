package com.example.api_monetization.entity;
import java.time.LocalDateTime;

import com.example.api_monetization.enums.ApprovalStatus;
import com.example.api_monetization.enums.PublishRequestStatus;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "api_publish_requests")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiPublishRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "api_id", nullable = false)
	private Api api;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "requested_by", nullable = false)
	private User requestedBy;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "approved_by")
	private User approvedBy;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "request_status", length = 30)
	private PublishRequestStatus requestStatus;
	
	@Column(name = "request_date")
	private LocalDateTime requestDate;
	
	@Column(name = "approval_date")
	private LocalDateTime approvalDate;
	
	@Column(name = "rejection_reason",columnDefinition = "TEXT")
	private String rejectionReason;
	
	
	
	
}
