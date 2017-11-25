package com.kikia.itacon.controllers;

import java.security.Principal;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kikia.itacon.domain.User;
import com.kikia.itacon.dto.UserDTO;
import com.kikia.itacon.services.UserService;

@Controller
public class LoginController {

	private UserService userService;
	private ModelMapper modelMapper;
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	public LoginController(UserService userService, ModelMapper modelMapper) {
		this.userService = userService;
		this.modelMapper = modelMapper;
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
		logger.debug(user.toString());

		/*Converting entity object user to DTO user*/
		UserDTO userDTO = convertToDTO(user);
		model.addAttribute("userDTO", userDTO);

		return "dashboard";
	}
	
	/* Convert Entity to Data Transfer Object (DTO) and vice-versa */
	/**
	 * Converts Entity - domain model object to the DTO object
	 * 
	 * @param user
	 *            - receives domain model as parameter
	 * @return DTO object
	 */
	private UserDTO convertToDTO(User user) {
		UserDTO userDTO = modelMapper.map(user, UserDTO.class);
		return userDTO;
	}

}
