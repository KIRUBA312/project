package com.example.auth_server.service;

import java.util.List;

import com.example.auth_server.entity.Permission;

public interface PermissionService {

	Permission createPermission(Permission permission);

	List<Permission> getAllPermissions();

	Permission getPermissionById(Long id);

	Permission updatePermission(Long id, Permission permission);

	String deletePermission(Long id);

}
