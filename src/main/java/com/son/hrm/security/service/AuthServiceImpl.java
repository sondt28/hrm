package com.son.hrm.security.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.son.hrm.security.dto.LoginDto;
import com.son.hrm.security.jwt.JwtHelper;
import com.son.hrm.user.model.User;
import com.son.hrm.user.repository.UserRepository;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private UserRepository userRepository;
		
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private JwtHelper jwt;
	
	@Override
	public String login(LoginDto dto) {
		
		Optional<User> userOpt = userRepository.findByEmail(dto.getEmail());
		
		String encodedPassword = userOpt.get().getPassword();
		
		if (encoder.matches(dto.getPassword(), encodedPassword)) {
			return jwt.generateJwtToken(dto.getEmail());
		}
		
		return null;
	}

}
