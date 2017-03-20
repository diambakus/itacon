package com.kikia.itacon.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.kikia.itacon.entities.User;
import com.kikia.itacon.services.UserService;

@Controller
public class UserController {

	private UserService userService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@GetMapping(value="/newuser")
	public String registerNewUser(Model model) {
		model.addAttribute("user", new User());
		return "newuser";
	}
}
