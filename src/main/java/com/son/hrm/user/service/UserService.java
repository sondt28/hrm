package com.son.hrm.user.service;

import java.util.List;

import com.son.hrm.user.model.User;

public interface UserService {
	User createUser(User user);
	List<User> getAllUser();
	User getUserById(Integer id);
	User updateUser(Integer id, User user);
	boolean deleteUser(Integer Id);
}
