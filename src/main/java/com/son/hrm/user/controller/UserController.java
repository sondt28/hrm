package com.son.hrm.user.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.son.hrm.common.utils.ResponseHelper;
import com.son.hrm.user.model.User;
import com.son.hrm.user.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@GetMapping
	public Object getAllUser() {
		
		List<User> users = service.getAllUser();
		
		return ResponseHelper.getResponse(users, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public Object getUserById(@PathVariable("id") Integer id) {
		User user = service.getUserById(id);
		
		if (user == null)
			return ResponseHelper.getErrorResponse("User not found.", 
													HttpStatus.BAD_REQUEST);
		
		return ResponseHelper.getResponse(user, HttpStatus.OK);
	}
	
	@PostMapping("/create-user")
	public Object createUser(@RequestBody User user, 
							BindingResult errors) {
		if (errors.hasErrors())
			return ResponseHelper.getErrorResponse(errors, HttpStatus.BAD_REQUEST);
		
		service.createUser(user);
		
		return ResponseHelper.getResponse(user, HttpStatus.CREATED);
	}
	
	@PutMapping("{id}")
	public Object updateUserById(@PathVariable("id") Integer id, 
								@RequestBody User user,
								BindingResult errors) {
		
		if (errors.hasErrors())
			return ResponseHelper.getErrorResponse(errors, HttpStatus.BAD_REQUEST);
		
		User userUpdate = service.updateUser(id, user);
		
		return ResponseHelper.getResponse(userUpdate, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public Object deleteUserById(@PathVariable("id") Integer id) {
		
		boolean isDeleted = service.deleteUser(id);
		
		if (!isDeleted) 
			return ResponseHelper.getErrorResponse("Delete failed! User not found.", 
													HttpStatus.BAD_REQUEST);
		
		return ResponseHelper.getResponse("Delete successfuly!", HttpStatus.OK);
	}
}
