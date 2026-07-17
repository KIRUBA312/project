package com.example.api_monetization.dto.notification;

import java.time.LocalDateTime;

import com.example.api_monetization.enums.NotificationType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationResponse {

	private Long id;
	private Long userId;
	private String title;
	private String message;
	private NotificationType notificationType;
	private Boolean isRead;
	private LocalDateTime createdAt;
	
	
}
