package com.kikia.itacon.controllers.institution;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kikia.itacon.domain.Institution;
import com.kikia.itacon.domain.User;
import com.kikia.itacon.services.InstitutionService;
import com.kikia.itacon.services.UserService;

@Controller
@RequestMapping("/institution")
public class InstitutionController {

	private InstitutionService institutionService;
	private UserService userService;
	private final String institutionsListViewValue = "institutions/institutions";

	@Autowired
	public void InstituteController(InstitutionService institutionService, UserService userService) {
		this.institutionService = institutionService;
		this.userService = userService;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {

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
	public String institutions(Model model, final Principal principal) {
		User user = userService.findByUsername(principal.getName());
		String viewValue = null;

		if (user != null) {
			model.addAttribute("user", user);

			model.addAttribute("institution", new Institution());
			model.addAttribute("institutions", institutionService.listAllInstitutions());
			viewValue = institutionsListViewValue;
		} else {
			viewValue = "unexpected_error";
		}
		return viewValue;
	}

	/**
	 * 
	 * @param model
	 * @param principal
	 * @return
	 */
	@GetMapping(value = "/addInstitution")
	public String addInstitution(Model model, Principal principal) {
		User user = userService.findByUsername(principal.getName());
		String viewValue = null;

		if (user != null) {
			model.addAttribute("user", user);
			model.addAttribute("institution", new Institution());
			viewValue = institutionsListViewValue;
		} else {
			viewValue = "unexpected_error";
		}
		return viewValue;

	}

	/**
	 * 
	 * @param model
	 * @param institution
	 * @param bindingResult
	 * @param principal
	 * @return
	 */
	@PostMapping(value = "/addInstitution")
	public String addInstitution(Model model, @ModelAttribute("institution") Institution institution,
			BindingResult bindingResult, final Principal principal) {

		User user = userService.findByUsername(principal.getName());

		if (user != null) {
			institutionService.saveInstitution(institution);
			model.addAttribute("user", user);
		}

		return "redirect:/institution/institutions";
	}
	
	@GetMapping(value="/show/{id}")
	public String showInstitutionInfo(Model model, Principal principal, @PathVariable("id") Long id) {
		
		String view = "unexpected_error";
		User user = userService.findByUsername(principal.getName());
		if (user != null) {
			model.addAttribute("user", user);
			model.addAttribute("institution", institutionService.getInstitution(id));
			view = "institutions/institution";
		}
		return view;
	}
}
