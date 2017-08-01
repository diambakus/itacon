package com.kikia.itacon.controllers.publicservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kikia.itacon.entities.PublicInstitution;
import com.kikia.itacon.services.PublicInstitutionService;
import com.kikia.itacon.utils.CodeGenerator;

@Controller
@RequestMapping("/publicservices")
public class PublicServiceController {

	private PublicInstitutionService publicInstitutionService;

	@Autowired
	public void setPublicInstitutionService(PublicInstitutionService publicInstitutionService) {
		this.publicInstitutionService = publicInstitutionService;
	}

	@GetMapping("/")
	public String showPublicServiceMain(Model model) {
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
