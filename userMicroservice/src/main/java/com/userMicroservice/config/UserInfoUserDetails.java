package com.userMicroservice.config;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.userMicroservice.model.Role;
import com.userMicroservice.model.User;

public class UserInfoUserDetails implements UserDetails {

	private static final long serialVersionUID = 1L;
	private String name;
	private String password;
	private String email;

	private List<GrantedAuthority> roles;

	public UserInfoUserDetails(User userInfo) {
		name = userInfo.getUserName();
		password = userInfo.getPassword();
		email = userInfo.getEmail();

		roles = userInfo.getRoles()
		        .stream().map((role)-> new SimpleGrantedAuthority(role.getName()))
		        .collect(Collectors.toList());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}

