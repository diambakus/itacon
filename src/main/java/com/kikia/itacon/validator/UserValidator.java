package com.kikia.itacon.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.kikia.itacon.entities.User;
import com.kikia.itacon.services.UserService;

@Component
public class UserValidator implements Validator {

	private UserService userService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		User user = (User) target;
		int usernameLength, passwordLength;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
		usernameLength = user.getUsername().length();
		if (!((usernameLength >= 6) && (usernameLength <= 32))) {
			errors.rejectValue("username", "Size.userForm.username");
		}
		if (userService.findByUsername(user.getUsername()) != null) {
			errors.rejectValue("username", "Duplicate.userForm.username");
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
		passwordLength = user.getPassword().length();
		if (!((passwordLength >= 8) && (passwordLength <= 32))) {
			errors.rejectValue("password", "Size.userForm.password");
		}

		if (!user.getPasswordConfirm().equals(user.getPassword())) {
			errors.rejectValue("passwordConfirm", "diff.userForm.password");
		}
	}

}
