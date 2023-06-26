package com.userMicroservice.config;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.userMicroservice.model.User;
import com.userMicroservice.repository.UserRepository;

@Component
public class UserInfoUserDetailsServices implements UserDetailsService{
	@Autowired
    private UserRepository repository; 
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		User credential = repository.findByEmail(email).get();
       // return credential.map(CustomUserDetails::new).orElseThrow(() -> new UsernameNotFoundException("user not found with name :" + username));
		List<GrantedAuthority> authorities = credential.getRoles()
		        .stream().map((role)-> new SimpleGrantedAuthority(role.getName()))
		        .collect(Collectors.toList());
		return new org.springframework.security.core.userdetails.User(credential.getEmail(),credential.getPassword(), authorities);
//		return credential.map(UserInfoUserDetails::new)
//				.orElseThrow(()->new UsernameNotFoundException("User not found"));
	}

}
