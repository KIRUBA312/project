package com.example.auth_server.service;

import java.util.List;

import com.example.auth_server.entity.Role;

public interface RoleService {

	Role createRole(Role role);

	List<Role> getAllRoles();

	Role getRoleById(Long id);

	Role updateRole(Long id, Role role);

	String deleteMapping(Long id);

}
