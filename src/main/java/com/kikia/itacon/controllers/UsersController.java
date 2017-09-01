package com.kikia.itacon.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.kikia.itacon.services.UserService;

@Controller
public class UsersController {

	private UserService userService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@GetMapping(value = "/dashboard/admin/users")
	public String listUsers(Model model) {
		model.addAttribute("users", userService.listAllUsers());
		return "admin/users";
	}
}
