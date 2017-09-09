package com.kikia.itacon.controllers.publicservices;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kikia.itacon.domain.PublicInstitution;
import com.kikia.itacon.domain.User;
import com.kikia.itacon.services.PublicInstitutionService;
import com.kikia.itacon.services.UserService;
import com.kikia.itacon.utils.CodeGenerator;

@Controller
@RequestMapping("/publicservices")
public class PublicServiceController {

	private PublicInstitutionService publicInstitutionService;
	private UserService userService;

	@Autowired
	public PublicServiceController(PublicInstitutionService publicInstitutionService, UserService userService) {
		this.publicInstitutionService = publicInstitutionService;
		this.userService = userService;
	}

	@GetMapping("/")
	public String showPublicServiceMain(Model model, Principal principal) {
		User user = userService.findByUsername(principal.getName());
		model.addAttribute("user", user);
		model.addAttribute("publicservices", publicInstitutionService.listAllPublicInstitutions());
		return "publicservices/publicservicesmain";
	}

	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("publicservices", publicInstitutionService.listAllPublicInstitutions());
		model.addAttribute("publicservice", new PublicInstitution());
		return "publicservices/publicservicescreate";
	}

	@PostMapping("/publicservicecreation")
	public String creation(@ModelAttribute("publicservice") PublicInstitution publicservice, Model model) {

		String publicServiceCode = CodeGenerator.getInstance().getPublicServiceCode(publicservice.getName());
		publicservice.setCode(publicServiceCode);
		publicInstitutionService.savePublicInstitution(publicservice);
		return "redirect:/publicservices/create";
	}
}
