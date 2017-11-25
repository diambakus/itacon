package com.kikia.itacon.controllers.publicservices;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kikia.itacon.domain.Institution;
import com.kikia.itacon.domain.User;
import com.kikia.itacon.services.InstitutionService;
import com.kikia.itacon.services.UserService;
import com.kikia.itacon.utils.CodeGenerator;

@Controller
@RequestMapping("/publicservices")
public class PublicServiceController {

	private InstitutionService institutionService;
	private UserService userService;

	@Autowired
	public PublicServiceController(InstitutionService institutionService, UserService userService) {
		this.institutionService = institutionService;
		this.userService = userService;
	}

	@GetMapping("/")
	public String showPublicServiceMain(Model model, Principal principal) {
		User user = userService.findByUsername(principal.getName());
		model.addAttribute("user", user);
		model.addAttribute("publicservices", institutionService.listAllInstitutions());
		return "publicservices/publicservicesmain";
	}

	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("publicservices", institutionService.listAllInstitutions());
		model.addAttribute("publicservice", new Institution());
		return "publicservices/publicservicescreate";
	}

	@PostMapping("/publicservicecreation")
	public String creation(@ModelAttribute("publicservice") Institution publicservice, Model model) {

		String publicServiceCode = CodeGenerator.getInstance().getPublicServiceCode(publicservice.getName());
		publicservice.setCode(publicServiceCode);
		//institutionService.saveInstitution(publicservice);
		return "redirect:/publicservices/create";
	}
}
