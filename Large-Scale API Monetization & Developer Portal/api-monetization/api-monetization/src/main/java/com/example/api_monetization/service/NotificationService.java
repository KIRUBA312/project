package com.example.api_monetization.service;

import java.util.List;

import com.example.api_monetization.dto.notification.NotificationResponse;
import com.example.api_monetization.dto.notification.UnreadCountResponse;

public interface NotificationService {

	List<NotificationResponse> getUserNotifications(Long userId);

	NotificationResponse getNotification(Long notificationId);

	void markAsRead(Long notificationId);

	UnreadCountResponse getUnreadCount(Long userId);

	void deleteNotification(Long notificationId);

}
