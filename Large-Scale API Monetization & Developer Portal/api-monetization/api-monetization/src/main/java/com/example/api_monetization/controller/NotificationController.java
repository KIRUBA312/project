package com.example.api_monetization.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api_monetization.dto.notification.NotificationResponse;
import com.example.api_monetization.dto.notification.UnreadCountResponse;
import com.example.api_monetization.service.NotificationService;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

	@Autowired
	private NotificationService notificationService;
	
	@GetMapping("/user/{userId}")
    public ResponseEntity<List<NotificationResponse>>
            getUserNotifications(@PathVariable Long userId) {

        return ResponseEntity.ok(
                notificationService.getUserNotifications(userId));
    }

   
    @GetMapping("/{notificationId}")
    public ResponseEntity<NotificationResponse>
            getNotification(@PathVariable Long notificationId) {

        return ResponseEntity.ok(
                notificationService.getNotification(notificationId));
    }


    @PatchMapping("/{notificationId}/read")
    public ResponseEntity<Void> markAsRead(
            @PathVariable Long notificationId) {

        notificationService.markAsRead(notificationId);

        return ResponseEntity.noContent().build();
    }

    
    @GetMapping("/user/{userId}/unread-count")
    public ResponseEntity<UnreadCountResponse>
            getUnreadCount(@PathVariable Long userId) {

        return ResponseEntity.ok(
                notificationService.getUnreadCount(userId));
    }

    @DeleteMapping("/{notificationId}")
    public ResponseEntity<Void> deleteNotification(
            @PathVariable Long notificationId) {

        notificationService.deleteNotification(notificationId);

        return ResponseEntity.noContent().build();
    }
}
