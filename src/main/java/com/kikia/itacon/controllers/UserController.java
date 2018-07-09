package com.kikia.itacon.controllers;

import java.security.Principal;
import java.util.Iterator;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kikia.itacon.domain.User;
import com.kikia.itacon.services.UserService;
import com.kikia.itacon.utils.TextUtils;

@Controller
public class UserController {

	private UserService userService;
	private TextUtils textUtils;

	@Autowired
	public UserController(UserService userService, TextUtils textUtils) {
		this.userService = userService;
		this.textUtils = textUtils;
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
	public String registration(@Valid @ModelAttribute("user") User user, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {

		String targetView;
		
		if (!textUtils.isValidName(user.getFirstName())) {
			bindingResult.rejectValue("firstName", "invalid_firstName.user", "Nome inserido é inválido!");
		}
		if (!textUtils.isValidName(user.getLastName())) {
			bindingResult.rejectValue("lastName", "invalid_lastName.user", "Sobrenome inserido é inválido!");
		}
		if (textUtils.isValidUsername(user.getUsername())) {
			User alreadyPersistedUser = userService.findByUsername(user.getUsername());
			if (alreadyPersistedUser != null) {
				bindingResult.rejectValue("username", "exist_username.user", "Já existe usuário com esse login!");
			}
		} else {
			bindingResult.rejectValue("username", "invalid_username.user", "Login inserido inválido!");
		}
		if (textUtils.isValidEmail(user.getEmail())) {
			User alreadyPersistedUser = userService.getUserByEmail(user.getEmail());
			if (alreadyPersistedUser != null) {
				bindingResult.rejectValue("email", "exist_email.user", "Já existe usuário com esse e-mail!");
			}

		} else {
			bindingResult.rejectValue("email", "email_invalid.user", "E-mail inserido é inválido!");
		}

		if (bindingResult.hasErrors()) {

			List<ObjectError> errors = bindingResult.getAllErrors();

			for (Iterator<ObjectError> iterator = errors.iterator(); iterator.hasNext();) {
				ObjectError objectError = (ObjectError) iterator.next();
				
				if (objectError.getCode().equals("invalid_firstName.user")) {
					redirectAttributes.addFlashAttribute("invalid_firstname", objectError.getDefaultMessage());
				}else if (objectError.getCode().equals("invalid_lastName.user")) {
					redirectAttributes.addFlashAttribute("invalid_laststname", objectError.getDefaultMessage());
				}else if (objectError.getCode().equals("exist_username.user")) {
					redirectAttributes.addFlashAttribute("exist_username", objectError.getDefaultMessage());
				}else if (objectError.getCode().equals("invalid_username.user")) {
					redirectAttributes.addFlashAttribute("invalid_username", objectError.getDefaultMessage());
				}else if (objectError.getCode().equals("exist_email.user")) {
					redirectAttributes.addFlashAttribute("exist_email", objectError.getDefaultMessage());
				}else if (objectError.getCode().equals("email_invalid.user")) {
					redirectAttributes.addFlashAttribute("invalid_email", objectError.getDefaultMessage());
				}
			}
			redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
			targetView = "registration";
		} else {
			userService.saveUser(user); // Create the new user
			redirectAttributes.addFlashAttribute("create_item_message",
					"Usuário registrado. Aguarde aprovação do adminstrador!");
			redirectAttributes.addFlashAttribute("alertClass", "alert-success");
			targetView = "redirect:/";
		}
		return targetView;
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
