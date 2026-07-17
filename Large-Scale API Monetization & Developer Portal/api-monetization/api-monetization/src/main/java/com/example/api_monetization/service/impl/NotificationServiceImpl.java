package com.example.api_monetization.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.api_monetization.dto.notification.NotificationResponse;
import com.example.api_monetization.dto.notification.UnreadCountResponse;
import com.example.api_monetization.entity.Notification;
import com.example.api_monetization.exception.ResourceNotFoundException;
import com.example.api_monetization.mapper.NotificationMapper;
import com.example.api_monetization.repository.NotificationRepository;
import com.example.api_monetization.repository.UserRepository;
import com.example.api_monetization.service.NotificationService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class NotificationServiceImpl implements NotificationService{

	@Autowired
	private NotificationRepository notificationRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private NotificationMapper notificationMapper;
	@Override
	public List<NotificationResponse> getUserNotifications(Long userId) {
		// TODO Auto-generated method stub
		if(!userRepository.existsById(userId)) {
			throw new ResourceNotFoundException("User not found");
		}
		return notificationRepository.findByUserIdOrderByCreatedAtDesc(userId)
				.stream().map(notificationMapper::toResponse)
				.toList();
	}
	@Override
	public NotificationResponse getNotification(Long notificationId) {
		// TODO Auto-generated method stub
		Notification notification=notificationRepository
				.findById(notificationId).orElseThrow(()->
				new ResourceNotFoundException("Notification not found"));
		return notificationMapper.toResponse(notification);
	}
	@Override
	public void markAsRead(Long notificationId) {
		// TODO Auto-generated method stub
		Notification notification =
                notificationRepository.findById(notificationId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Notification not found."));

        notification.setIsRead(true);

        notificationRepository.save(notification);
	}
	@Override
	public UnreadCountResponse getUnreadCount(Long userId) {
		// TODO Auto-generated method stub
		 if (!userRepository.existsById(userId)) {
	            throw new ResourceNotFoundException(
	                    "User not found.");
	        }

	        long count =
	                notificationRepository.countByUserIdAndIsReadFalse(
	                        userId);

	        return UnreadCountResponse.builder()
	                .unreadCount(count)
	                .build();
	    
	}
	@Override
	public void deleteNotification(Long notificationId) {
		// TODO Auto-generated method stub
		Notification notification =
                notificationRepository.findById(notificationId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Notification not found."));

        notificationRepository.delete(notification);
	}
	
	
}
