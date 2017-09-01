package com.kikia.itacon.controllers;

import java.security.Principal;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kikia.itacon.domain.User;
import com.kikia.itacon.services.UserService;

@Controller
public class LoginController {

	private UserService userService;
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@GetMapping(value = "/login")
	public String getLoginPage(Model model, @RequestParam Optional<String> error) {

		if (error != null)
			model.addAttribute("error", "Nome de usuário ou senha inválido(s).");

		return "login";
	}

	/**
	 * We're going to pass id as parameter to use it later on the page.
	 * 
	 * @return
	 */
	@GetMapping("/dashboard")
	public String userMainPage(Model model, Principal principal) {
		User user = userService.findByUsername(principal.getName());
		
		model.addAttribute("user", user);
		return "dashboard";
	}

}
