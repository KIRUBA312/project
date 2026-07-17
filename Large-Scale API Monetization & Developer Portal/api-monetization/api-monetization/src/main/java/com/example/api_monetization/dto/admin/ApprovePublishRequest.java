package com.example.api_monetization.dto.admin;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApprovePublishRequest {

    private Long adminId;

    private String comments;
}