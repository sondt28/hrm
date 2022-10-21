package com.son.hrm.security.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.son.hrm.user.model.User;
import com.son.hrm.user.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		User user = repository.findByEmail(email).orElseThrow(() -> 
						new UsernameNotFoundException("User not found."));
		
		return new org.springframework.security.core.userdetails.User(
					user.getEmail(), user.getPassword(), getGrantedAuthority(user));
	}
	
	private List<GrantedAuthority> getGrantedAuthority(User user) {
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(user.getRole().name()));
		
		return authorities;
	}
	
}
