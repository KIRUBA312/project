package com.example.auth_server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.auth_server.entity.Role;
import com.example.auth_server.repository.RoleRepository;
import com.example.auth_server.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Role createRole(Role role) {
		// TODO Auto-generated method stub
		return roleRepository.save(role);
	}

	@Override
	public List<Role> getAllRoles() {
		// TODO Auto-generated method stub
		return roleRepository.findAll();
	}

	@Override
	public Role getRoleById(Long id) {
		// TODO Auto-generated method stub
		return roleRepository.findById(id).orElseThrow(() ->
		new RuntimeException("Role Not Found"));
	}

	@Override
	public Role updateRole(Long id, Role role) {
		// TODO Auto-generated method stub
		Role existing = getRoleById(id);
		existing.setRoleName(role.getRoleName());
		existing.setPermissions(role.getPermissions());
		return roleRepository.save(existing);
	}

	@Override
	public String deleteMapping(Long id) {
		// TODO Auto-generated method stub
		Role role = getRoleById(id);
		roleRepository.delete(role);
		return "Role Deleted Successfully";
	}
	
	
}
