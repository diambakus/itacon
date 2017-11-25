package com.kikia.itacon.controllers;

import java.security.Principal;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.kikia.itacon.domain.User;
import com.kikia.itacon.dto.UserDTO;
import com.kikia.itacon.services.UserService;

@Controller
public class UsersController {

	private UserService userService;
	private ModelMapper modelMapper;

	@Autowired
	public UsersController(UserService userService, ModelMapper modelMapper) {
		this.userService = userService;
		this.modelMapper = modelMapper;
	}

	@GetMapping(value = "/dashboard/admin/users")
	public String listUsers(Model model, Principal principal) {
		User user = userService.findByUsername(principal.getName());
		UserDTO userDTO = convertToDTO(user);
		
        String viewValue = null;
        
		if (userDTO != null) {
			model.addAttribute("userDTO", userDTO);
			
			Iterable<User> users = userService.listAllUsers();
			Stream<User> usersStream = StreamSupport.stream(users.spliterator(), false);
			model.addAttribute("usersDTO", usersStream.map(userItem->convertToDTO(userItem)).collect(Collectors.toList()));
			viewValue = "admin/users";
		} else {
			viewValue = "unexpected_error";
		}
		
		return viewValue;
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
