package com.kikia.itacon.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.kikia.itacon.entities.User;
import com.kikia.itacon.services.SecurityService;
import com.kikia.itacon.services.UserService;
import com.kikia.itacon.validator.UserValidator;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private SecurityService securityService;
	private UserValidator userValidator;

	@Autowired
	public void setUserValidator(UserValidator userValidator) {
		this.userValidator = userValidator;

	}

	@GetMapping(value = "/registration")
	public String registratio(Model model) {
		model.addAttribute("userForm", new User());
		return "index";
	}

	@PostMapping(value = "/registration")
	public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
		userValidator.validate(userForm, bindingResult);

		if (bindingResult.hasErrors()) {
			return "users";
		}

		userService.saveUser(userForm);

		/*
		 * It will be changed once user must wait admin decision (on sign him up
		 * or not)
		 */
		securityService.autologin(userForm.getUsername(), userForm.getPassword());

		return "redirect:/users";
	}

	@GetMapping(value = "/dashboard")
	public String login(Model model, String error, String logout) {
		if (error != null)
			model.addAttribute("error", "Nome de usuário ou senha inválido(s).");

		if (logout != null)
			model.addAttribute("message", "Logout efetuado com sucesso!");

		return "dashboard";
	}

	@GetMapping(value = { "/", "/welcome" })
	public String welcome(Model model) {
		return "users";
	}
}
