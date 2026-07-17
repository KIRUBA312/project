package com.example.api_monetization.dto.admin;

import java.time.LocalDateTime;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmailTemplateResponse {

    private Long id;

    private String templateName;

    private String subject;

    private String body;

    private Boolean active;

    private LocalDateTime createdAt;

}