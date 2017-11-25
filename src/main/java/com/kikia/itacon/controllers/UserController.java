package com.kikia.itacon.controllers;

import java.security.Principal;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
import com.kikia.itacon.dto.UserDTO;
import com.kikia.itacon.services.UserService;
import com.kikia.itacon.validator.UserValidator;

@Controller
public class UserController {

	private UserService userService;
	private UserValidator userValidator;
	private ModelMapper modelMapper;

	@Autowired
	public UserController(UserService userService, ModelMapper modelMapper, UserValidator userValidator) {
		this.userService = userService;
		this.userValidator = userValidator;
		this.modelMapper = modelMapper;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(userValidator);
	}

	@GetMapping(value = "/registration")
	public String registrationForm(Model model) {
		model.addAttribute("userDTO", new UserDTO());
		return "registration";
	}

	@PostMapping(value = "/registration")
	public String registration(@Valid @ModelAttribute("userDTO") UserDTO userDTO, BindingResult bindingResult) {
		userValidator.validate(userDTO, bindingResult);

		if (bindingResult.hasErrors()) {
			return "registration";
		}
		try {
			User user = convertToEntity(userDTO);
			user.setPassword((new BCryptPasswordEncoder().encode(userDTO.getPassword())).toCharArray());
			userService.saveUser(user);
		} catch (DataIntegrityViolationException e) {
			bindingResult.rejectValue("email", "Email já existe!");
		}

		return "redirect:/";
	}

	@PreAuthorize("@currentUserServiceImpl.canAccessUser(principal, #id)")
	@GetMapping(value = "/user/profile/{id}")
	public String getUserPage(Model model, Principal principal, @PathVariable("id") Long id) {
		/* Logged user conversion */
		User user = userService.findByUsername(principal.getName());
		UserDTO userDTO = convertToDTO(user);

		/* action targeted user profile */
		User targetedUser = userService.getUserById(id);
		UserDTO targetedUserDTO = convertToDTO(targetedUser);

		model.addAttribute("userDTO", userDTO);
		model.addAttribute("userOnProfile", targetedUserDTO);

		return "user/userprofile";
	}

	@GetMapping(value = "/user/activate/{id}")
	public String statusUser(Model model, Principal principal, @PathVariable("id") Long id) {
		/* Once we deal with Id we don't need convert*/
		User targetedUser = userService.getUserById(id);
		if ((targetedUser != null) && (!targetedUser.isEnable()))
			targetedUser.setEnable(true);
		userService.saveUser(targetedUser);

		/*Converting logged user*/
		User user = userService.findByUsername(principal.getName());
		UserDTO userDTO = convertToDTO(user);
		model.addAttribute("userDTO", userDTO);
		
		/*Converting and updating targeted user*/
		UserDTO targetedUserDTO = convertToDTO(targetedUser);
		model.addAttribute("userOnProfile", targetedUserDTO);

		return "user/userprofile";
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

	/**
	 * As we persist through entity object. We need to convert DTO object to
	 * entity object. This method does that.
	 * 
	 * @param userDTO
	 *            - DTO object to be converted
	 * @return It returns entity object
	 */
	private User convertToEntity(UserDTO userDTO) {
		User user = modelMapper.map(userDTO, User.class);
		return user;
	}
}
