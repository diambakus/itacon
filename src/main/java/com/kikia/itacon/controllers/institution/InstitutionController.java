package com.kikia.itacon.controllers.institution;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kikia.itacon.domain.PublicInstitution;
import com.kikia.itacon.domain.User;
import com.kikia.itacon.services.PublicInstitutionService;
import com.kikia.itacon.services.UserService;

@Controller
@RequestMapping("/institution")
public class InstitutionController {

	private PublicInstitutionService institutionService;
	private UserService userService;

	@Autowired
	public void InstituteController(PublicInstitutionService publicInstitutionService, UserService userService) {
		this.institutionService = publicInstitutionService;
		this.userService = userService;
	}

	/**
	 * That method provide initial resources to the view. It lists all
	 * institutions available. And, it set up resource for POST (add) method.
	 * 
	 * @param model
	 *            -
	 * @param principal
	 *            - it provides resources of logged user
	 * @return - returns a view html that must be presented
	 */
	@GetMapping(value = "/institutions")
	public String institutions(Model model, Principal principal) {
		User user = userService.findByUsername(principal.getName());
		String viewValue = null;

		if (user != null) {
			model.addAttribute("user", user);
			model.addAttribute("institution", new PublicInstitution());
			viewValue = "institutions/institutions";
		} else {
			viewValue = "unexpected_error";
		}
		return viewValue;
	}
}
