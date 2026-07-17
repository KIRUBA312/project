package com.example.api_monetization.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.api_monetization.dto.notification.NotificationResponse;
import com.example.api_monetization.entity.Notification;

@Mapper(config = MapperConfig.class)
public interface NotificationMapper {

	@Mapping(source = "user.id",target = "userId")
	NotificationResponse toResponse(Notification entity);
}
