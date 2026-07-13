package com.example.enterprise_iam.util;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.enterprise_iam.dto.request.PermissionRequestDto;
import com.example.enterprise_iam.dto.request.RoleRequestDto;
import com.example.enterprise_iam.dto.request.UserRegistrationRequestDto;
import com.example.enterprise_iam.dto.request.UserUpdateRequestDto;
import com.example.enterprise_iam.dto.response.AuditLogResponseDto;
import com.example.enterprise_iam.dto.response.PermissionResponseDto;
import com.example.enterprise_iam.dto.response.RoleResponseDto;
import com.example.enterprise_iam.dto.response.UserResponseDto;
import com.example.enterprise_iam.entity.AuditLog;
import com.example.enterprise_iam.entity.Permission;
import com.example.enterprise_iam.entity.Role;
import com.example.enterprise_iam.entity.RolePermission;
import com.example.enterprise_iam.entity.User;
import com.example.enterprise_iam.entity.UserRole;

@Component
public class MapperUtil {

    // ===================================================
    // USER
    // ===================================================

    public User toUser(UserRegistrationRequestDto dto) {

        if (dto == null) {
            return null;
        }

        User user = new User();

        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setPhone(dto.getPhone());

        return user;
    }

    public void updateUser(User user, UserUpdateRequestDto dto) {

        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setPhone(dto.getPhone());
    }

    public UserResponseDto toUserResponse(User user) {

        if (user == null) {
            return null;
        }

        UserResponseDto dto = new UserResponseDto();

        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setPhone(user.getPhone());

        dto.setEnabled(user.getEnabled());
        dto.setAccountNonLocked(user.getAccountNonLocked());
        dto.setEmailVerified(user.getEmailVerified());
        dto.setMfaEnabled(user.getMfaEnabled());
        dto.setFailedAttempts(user.getFailedAttempts());

        dto.setCreatedAt(user.getCreatedAt());
        dto.setUpdatedAt(user.getUpdatedAt());

        if (user.getUserRoles() != null) {

            Set<String> roles = user.getUserRoles()
                    .stream()
                    .map(UserRole::getRole)
                    .map(Role::getName)
                    .collect(Collectors.toSet());

            dto.setRoles(roles);
        }

        return dto;
    }

    // ===================================================
    // ROLE
    // ===================================================

    public Role toRole(RoleRequestDto dto) {

        if (dto == null) {
            return null;
        }

        Role role = new Role();

        role.setName(dto.getName());
        role.setDescription(dto.getDescription());

        return role;
    }

    public void updateRole(Role role, RoleRequestDto dto) {

        role.setName(dto.getName());
        role.setDescription(dto.getDescription());
    }

    public RoleResponseDto toRoleResponse(Role role) {

        if (role == null) {
            return null;
        }

        RoleResponseDto dto = new RoleResponseDto();

        dto.setId(role.getId());
        dto.setName(role.getName());
        dto.setDescription(role.getDescription());

        if (role.getRolePermissions() != null) {

            Set<String> permissions = role.getRolePermissions()
                    .stream()
                    .map(RolePermission::getPermission)
                    .map(Permission::getName)
                    .collect(Collectors.toSet());

            dto.setPermissions(permissions);
        }

        return dto;
    }

    // ===================================================
    // PERMISSION
    // ===================================================

    public Permission toPermission(PermissionRequestDto dto) {

        if (dto == null) {
            return null;
        }

        Permission permission = new Permission();

        permission.setName(dto.getName());
        permission.setDescription(dto.getDescription());

        return permission;
    }

    public void updatePermission(Permission permission,
                                 PermissionRequestDto dto) {

        permission.setName(dto.getName());
        permission.setDescription(dto.getDescription());
    }

    public PermissionResponseDto toPermissionResponse(Permission permission) {

        if (permission == null) {
            return null;
        }

        PermissionResponseDto dto = new PermissionResponseDto();

        dto.setId(permission.getId());
        dto.setName(permission.getName());
        dto.setDescription(permission.getDescription());

        return dto;
    }

    // ===================================================
    // AUDIT LOG
    // ===================================================

    public AuditLogResponseDto toAuditLogResponse(AuditLog auditLog) {

        if (auditLog == null) {
            return null;
        }

        AuditLogResponseDto dto = new AuditLogResponseDto();

        dto.setId(auditLog.getId());
        dto.setAction(auditLog.getAction());
        dto.setEntityName(auditLog.getEntityName());
        dto.setEntityId(auditLog.getEntityId());
        dto.setPerformedBy(auditLog.getPerformedBy());
        dto.setPerformedAt(auditLog.getPerformedAt());
        dto.setDetails(auditLog.getDetails());

        return dto;
    }

}