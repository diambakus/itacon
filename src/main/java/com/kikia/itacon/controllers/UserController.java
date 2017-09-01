package com.kikia.itacon.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.kikia.itacon.dto.UserDTO;
import com.kikia.itacon.services.UserService;
import com.kikia.itacon.validator.UserValidator;

@Controller
public class UserController {

	private UserService userService;
	private UserValidator userValidator;

	@Autowired
	public UserController(UserService userService, UserValidator userValidator) {
		this.userService = userService;
		this.userValidator = userValidator;
	}

	@InitBinder("form")
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(userValidator);
	}

	@GetMapping(value = "/registration")
	public String registrationForm(Model model) {
		model.addAttribute("form", new UserDTO());
		return "registration";
	}
	
	@PostMapping(value = "/registration")
	public String registration(@Valid @ModelAttribute("form") UserDTO user, BindingResult bindingResult) {
		userValidator.validate(user, bindingResult);

		if (bindingResult.hasErrors()) {
			return "registration";
		}
		try {
			userService.saveUser(user);
		} catch (DataIntegrityViolationException e) {
			bindingResult.rejectValue("emails.exists", "Email já existe!");
		}

		return "redirect:/";
	}
	
	@PreAuthorize("@currentUserServiceImpl.canAccessUser(principal, #id)")
	@GetMapping("/profile/{id}")
	public String getUserPage(Model model, @PathVariable Long id) {
		return "userprofile";
	}
}
