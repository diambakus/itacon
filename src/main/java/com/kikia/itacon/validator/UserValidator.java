package com.kikia.itacon.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.kikia.itacon.dto.UserDTO;
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
		return clazz.equals(UserDTO.class);
	}

	@Override
	public void validate(Object target, Errors errors) {

		UserDTO form = (UserDTO) target;

		validateUsername(form, errors);
		validatePassword(form, errors);
		validateEmail(form, errors);
	}
	
	

	private void validateEmail(UserDTO form, Errors errors) {
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");
		if (userService.getUserByEmail(form.getEmail()).isPresent()) {
			errors.rejectValue("email.exists", "Usuário com esse e-mail já existe!");
		}
	}

	private void validatePassword(UserDTO form, Errors errors) {
		
		int passwordLength;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
		passwordLength = form.getPassword().length();
		if (!((passwordLength >= 8) && (passwordLength <= 32))) {
			errors.rejectValue("password", "Size.userForm.password");
		}

		if (!form.getPasswordConfirm().equals(form.getPassword())) {
			errors.rejectValue("passwordConfirm", "diff.userForm.password");
		}
	}

	private void validateUsername(UserDTO form, Errors errors) {
		
		int usernameLength;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
		usernameLength = form.getUsername().length();
		if (!((usernameLength >= 6) && (usernameLength <= 32))) {
			errors.rejectValue("username", "Size.userForm.username");
		}
		if (userService.findByUsername(form.getUsername()) != null) {
			errors.rejectValue("username", "Duplicate.userForm.username");
		}
	}

}
