package com.kikia.itacon.controllers.offeredservice;

import java.math.BigDecimal;
import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kikia.itacon.converter.BigDecimalConverter;
import com.kikia.itacon.domain.OfferedService;
import com.kikia.itacon.domain.User;
import com.kikia.itacon.services.InstitutionService;
import com.kikia.itacon.services.OfferedServiceService;
import com.kikia.itacon.services.UserService;

@Controller
@RequestMapping("/offeredservice")
public class OfferedServiceController {

	private UserService userService;
	private OfferedServiceService offeredServiceService;
	private InstitutionService institutionService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
       binder.registerCustomEditor(BigDecimal.class, new BigDecimalConverter());
	}

	@Autowired
	public OfferedServiceController(UserService userService, OfferedServiceService offeredServiceService,
			InstitutionService institutionService) {
		this.userService = userService;
		this.offeredServiceService = offeredServiceService;
		this.institutionService = institutionService;
	}

	@GetMapping("/create")
	public String addOfferedService(Model model, Principal principal) {
		String targetView = "unexpected_error";
		User user = userService.findByUsername(principal.getName());
		if (user != null) {
			model.addAttribute("user", user);
			model.addAttribute("offeredService", new OfferedService());

			model.addAttribute("institutions", institutionService.listAllInstitutions());
			targetView = "offeredservice/create_offeredService";
		}
		return targetView;
	}

	@PostMapping("/submit")
	public String submitOfferedService(Model model,
			@Valid @ModelAttribute("offeredService") OfferedService offeredService, BindingResult bindingResult,
			Principal principal) {

		model.addAttribute("institutions", institutionService.listAllInstitutions());

		User user = userService.findByUsername(principal.getName());
		if (user != null) {
			offeredServiceService.saveOfferedService(offeredService);
			model.addAttribute("user", user);
		}

		return "undefined";
	}
}
