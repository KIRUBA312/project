package com.example.api_monetization.dto.api;

import java.time.LocalDateTime;

import com.example.api_monetization.enums.PublishRequestStatus;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiPublishResponse {

    private Long id;

    private Long apiId;

    private Long requestedBy;

    private Long approvedBy;

    private PublishRequestStatus requestStatus;

    private LocalDateTime requestDate;

    private LocalDateTime approvalDate;

    private String rejectionReason;

}