package com.son.hrm.user.validation.validator;

import java.util.Optional;
import java.util.logging.Logger;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.son.hrm.user.model.User;
import com.son.hrm.user.repository.UserRepository;
import com.son.hrm.user.validation.annotation.UniqueEmail;


public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

	private Logger logger = Logger.getLogger(getClass().getName());
	
	@Autowired
	private UserRepository repository;
	
	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		logger.info("Email: " + email);
		
		Optional<User> userOpt = repository.findByEmail(email);
		
		if (userOpt.isEmpty()) {
			
			return true;
		}

		return false;
	}
	

}
