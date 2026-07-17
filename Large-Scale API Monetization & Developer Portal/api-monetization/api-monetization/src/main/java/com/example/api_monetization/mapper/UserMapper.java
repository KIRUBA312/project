package com.example.api_monetization.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.example.api_monetization.dto.user.UserRequest;
import com.example.api_monetization.dto.user.UserResponse;
import com.example.api_monetization.entity.User;

@Mapper(config = MapperConfig.class)
public interface UserMapper {

	User toEntity(UserRequest dto);
	UserResponse toResponse(User entity);
	
	@Mapping(target = "id",ignore = true)
	@Mapping(target = "password",ignore = true)
	void updateEntity(UserRequest dto,@MappingTarget User entity);
}
