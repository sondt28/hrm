package com.son.hrm.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.son.hrm.common.utils.ResponseHelper;
import com.son.hrm.security.dto.LoginDto;
import com.son.hrm.security.service.AuthService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

	@Autowired
	private AuthService service;
	
	@PostMapping("login")
	public Object login(@RequestBody LoginDto dto) {
		
		String jwt = service.login(dto);
		
		if (jwt == null) 
			return ResponseHelper.getErrorResponse("Cannot authenticated." + dto.getEmail(), 
												HttpStatus.BAD_REQUEST);
		
		return ResponseHelper.getResponse(jwt, HttpStatus.OK);
	}
}
