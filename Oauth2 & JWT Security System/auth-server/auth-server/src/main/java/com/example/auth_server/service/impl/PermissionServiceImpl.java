package com.example.auth_server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.auth_server.entity.Permission;
import com.example.auth_server.repository.PermissionRepository;
import com.example.auth_server.service.PermissionService;

@Service
public class PermissionServiceImpl implements PermissionService{

	@Autowired
	private PermissionRepository permissionRepository;

	@Override
	public Permission createPermission(Permission permission) {
		// TODO Auto-generated method stub
		return permissionRepository.save(permission);
	}

	@Override
	public List<Permission> getAllPermissions() {
		// TODO Auto-generated method stub
		return permissionRepository.findAll();
	}

	@Override
	public Permission getPermissionById(Long id) {
		// TODO Auto-generated method stub
		return permissionRepository.findById(id)
				.orElseThrow(() -> 
				new RuntimeException("Permission Not Found"));
	}

	@Override
	public Permission updatePermission(Long id, Permission permission) {
		// TODO Auto-generated method stub
		Permission existing = getPermissionById(id);
		existing.setPermissionName(permission
				.getPermissionName());
		return permissionRepository.save(existing);
	}

	@Override
	public String deletePermission(Long id) {
		// TODO Auto-generated method stub
		Permission permission = getPermissionById(id);
		permissionRepository.delete(permission);
		return "Permission Deleted Successfully";
	}
	
}
