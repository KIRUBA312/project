package com.example.api_monetization.dto.admin;

import java.time.LocalDateTime;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SystemSettingResponse {

    private Long id;

    private String settingKey;

    private String settingValue;

    private String description;

    private Long updatedBy;

    private LocalDateTime updatedAt;

}