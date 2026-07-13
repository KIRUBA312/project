package com.example.enterprise_iam.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.enterprise_iam.entity.Permission;
import com.example.enterprise_iam.entity.Role;
import com.example.enterprise_iam.entity.RolePermission;
import com.example.enterprise_iam.entity.User;
import com.example.enterprise_iam.entity.UserRole;

public class CustomUserDetails implements UserDetails{
	
	private final User user;
	
	public CustomUserDetails(User user) {
		this.user = user;
	}
	
	public User getUser() {
		return user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
         List<GrantedAuthority> authorities = new java.util.ArrayList<>();

    for (UserRole userRole : user.getUserRoles()) {

        Role role = userRole.getRole();

        authorities.add(new SimpleGrantedAuthority(role.getName()));

        for (RolePermission rp : role.getRolePermissions()) {

            authorities.add(
                    new SimpleGrantedAuthority(
                            rp.getPermission().getName()));
        }
    }
				
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getEmail();
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
    public boolean isAccountNonLocked() {
        return Boolean.TRUE.equals(user.getAccountNonLocked());
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return Boolean.TRUE.equals(user.getEnabled());
    }

}
