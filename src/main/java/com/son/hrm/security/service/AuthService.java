package com.son.hrm.security.service;

import com.son.hrm.security.dto.LoginDto;

public interface AuthService {
	String login(LoginDto dto);
}
