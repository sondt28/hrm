package com.son.hrm.user.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.son.hrm.user.model.User;
import com.son.hrm.user.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Override
	public User createUser(User user) {
		String passwordEncoder = encoder.encode(user.getPassword());
		user.setPassword(passwordEncoder);
		
		User createUser = repository.save(user);
		
		createUser.setPassword("");
		
		return createUser;
	}

	@Override
	public List<User> getAllUser() {
		
		return repository.findAll();
	}

	@Override
	public User getUserById(Integer id) {
		Optional<User> userOpt = repository.findById(id);
		
		if (userOpt.isEmpty())
			return null;
		
		User userCurrent = userOpt.get();
		
		return userCurrent;
	}

	@Override
	public User updateUser(Integer id, User user) {
		Optional<User> userOpt = repository.findById(id);
		
		if (userOpt.isEmpty())
			return null;
		
		User userCurrent = userOpt.get();
		userCurrent.setEmail(user.getEmail());
		userCurrent.setAddress(user.getAddress());
		userCurrent.setFirstName(user.getFirstName());
		userCurrent.setGroup(user.getGroup());
		userCurrent.setLastName(user.getLastName());
		userCurrent.setPhoneNumber(user.getPhoneNumber());
		userCurrent.setTasks(user.getTasks());
		
		repository.save(userCurrent);
		
		return userCurrent;
	}

	@Override
	public boolean deleteUser(Integer id) {
		Optional<User> userOpt = repository.findById(id);
		
		if (userOpt.isEmpty())
			return false;
		
		repository.deleteById(id);
		return true;
	}
	
}
