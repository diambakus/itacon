package com.kikia.itacon.controllers;

import java.security.Principal;

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

import com.kikia.itacon.domain.User;
import com.kikia.itacon.services.UserService;

@Controller
public class UserController {

	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {

	}

	@GetMapping(value = "/registration")
	public String registrationForm(Model model) {
		model.addAttribute("user", new User());
		return "registration";
	}

	@PostMapping(value = "/registration")
	public String registration(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "registration";
		}
		try {
			userService.saveUser(user);
		} catch (DataIntegrityViolationException e) {
			bindingResult.rejectValue("email", "Email já existe!");
		}

		return "redirect:/";
	}

	@PreAuthorize("@currentUserServiceImpl.canAccessUser(principal, #id)")
	@GetMapping(value = "/user/profile/{id}")
	public String getUserPage(Model model, Principal principal, @PathVariable("id") Long id) {
		/* Logged user */
		User user = userService.findByUsername(principal.getName());

		/* action targeted user profile */
		User targetedUser = userService.getUserById(id);

		model.addAttribute("user", user);
		model.addAttribute("userOnProfile", targetedUser);

		return "user/userprofile";
	}

	@GetMapping(value = "/user/activate/{id}")
	public String statusUser(Model model, Principal principal, @PathVariable("id") Long id) {

		User targetedUser = userService.getUserById(id);
		if ((targetedUser != null) && (!targetedUser.isEnable()))
			userService.updateStatus(id, true);

		/* logged user */
		User user = userService.findByUsername(principal.getName());

		model.addAttribute("user", user);

		/* Converting and updating targeted user */
		model.addAttribute("userOnProfile", targetedUser);

		return "user/userprofile";
	}
}
