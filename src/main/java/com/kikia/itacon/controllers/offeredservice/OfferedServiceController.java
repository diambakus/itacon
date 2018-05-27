package com.kikia.itacon.controllers.offeredservice;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Iterator;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kikia.itacon.converter.BigDecimalConverter;
import com.kikia.itacon.domain.OfferedService;
import com.kikia.itacon.domain.User;
import com.kikia.itacon.services.InstitutionService;
import com.kikia.itacon.services.OfferedServiceService;
import com.kikia.itacon.services.UserService;
import com.kikia.itacon.utils.TextUtils;

@Controller
@RequestMapping("/offeredservice")
public class OfferedServiceController {

	private UserService userService;
	private OfferedServiceService offeredServiceService;
	private InstitutionService institutionService;
	private TextUtils textUtils;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(BigDecimal.class, new BigDecimalConverter());
	}

	@Autowired
	public OfferedServiceController(UserService userService, OfferedServiceService offeredServiceService,
			InstitutionService institutionService, TextUtils textUtils) {
		this.userService = userService;
		this.offeredServiceService = offeredServiceService;
		this.institutionService = institutionService;
		this.textUtils = textUtils;
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
			Principal principal, RedirectAttributes redirectAttributes) {

		String targetView = "redirect:/offeredservice/create";
		model.addAttribute("institutions", institutionService.listAllInstitutions());

		User user = userService.findByUsername(principal.getName());
		if (user != null) {

			if (textUtils.isValidName(offeredService.getName())) {
				OfferedService alreadyPersistedOfferedService = offeredServiceService
						.findOfferedServiceByName(offeredService.getName());
				if (alreadyPersistedOfferedService != null) {
					bindingResult.rejectValue("name", "exist.offeredservice", "Já existe serviço com esse nome!");
				} else {
					offeredServiceService.saveOfferedService(offeredService);
				}
			} else {
				bindingResult.rejectValue("name", "invalid.offeredservice",
						"Nome inválido!\n" + "Nome do serviço não pode ser vazio.\n"
								+ "Deve começar com letra maiúscula.\n Pode conter hífen e espaço. "
								+ "\nPode ter letras(acentuadas).");
			}

			model.addAttribute("user", user);
		}

		if (bindingResult.hasErrors()) {

			List<ObjectError> errors = bindingResult.getAllErrors();

			for (Iterator<ObjectError> iterator = errors.iterator(); iterator.hasNext();) {
				ObjectError objectError = (ObjectError) iterator.next();

				if (objectError.getCode().equals("exist.offeredservice")) {
					redirectAttributes.addFlashAttribute("create_item_message", objectError.getDefaultMessage());
				} else if (objectError.getCode().equals("invalid.offeredservice")) {
					redirectAttributes.addFlashAttribute("create_item_message", objectError.getDefaultMessage());
				} else {
					redirectAttributes.addFlashAttribute("create_item_message", "Cadastro de serviço falhou!");
				}
			}
			redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
			return targetView;
		}

		redirectAttributes.addFlashAttribute("create_item_message", "Item serviço criado com sucesso!");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		return targetView;
	}
}
