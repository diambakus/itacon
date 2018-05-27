package com.kikia.itacon.controllers.institution;

import java.security.Principal;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kikia.itacon.domain.Institution;
import com.kikia.itacon.domain.User;
import com.kikia.itacon.services.InstitutionService;
import com.kikia.itacon.services.UserService;
import com.kikia.itacon.utils.TextUtils;

@Controller
@RequestMapping("/institution")
public class InstitutionController {

	private InstitutionService institutionService;
	private UserService userService;
	private final String institutionsListViewValue = "institutions/institutions";
	private TextUtils textUtils;

	@Autowired
	public void InstituteController(InstitutionService institutionService, UserService userService,
			TextUtils textUtils) {
		this.institutionService = institutionService;
		this.userService = userService;
		this.textUtils = textUtils;
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
			BindingResult bindingResult, final Principal principal, RedirectAttributes redirectAttributes) {

		User user = userService.findByUsername(principal.getName());
		final String targetView = "redirect:/institution/institutions";

		if (user != null) {

			if (textUtils.isValidName(institution.getName())) {

				Institution alreadyPersistedInstitution = institutionService
						.findInstitutionByName(institution.getName());
				if (alreadyPersistedInstitution != null) {
					bindingResult.rejectValue("name", "exist.institution", "Já existe instituição com esse nome!");
				} else {
					institutionService.saveInstitution(institution);
				}
			} else {
				bindingResult.rejectValue("name", "invalid.institution",
						"Nome inválido!\n" +"Nome do instituição não pode ser vazio.\n"
								+ "Deve começar com letra maiúscula.\n Pode conter hífen e espaço. "
								+ "\nPode ter letras(acentuadas).");
			}
			model.addAttribute("user", user);
		}

		if (bindingResult.hasErrors()) {

			List<ObjectError> errors = bindingResult.getAllErrors();

			for (Iterator<ObjectError> iterator = errors.iterator(); iterator.hasNext();) {
				ObjectError objectError = (ObjectError) iterator.next();

				if (objectError.getCode().equals("exist.institution")) {
					redirectAttributes.addFlashAttribute("create_item_message", objectError.getDefaultMessage());
				} else if (objectError.getCode().equals("invalid.institution")) {
					redirectAttributes.addFlashAttribute("create_item_message", objectError.getDefaultMessage());
				} else {
					redirectAttributes.addFlashAttribute("create_item_message", "Cadastro de instituição falhou!");
				}
			}
			redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
			return targetView;
		}

		redirectAttributes.addFlashAttribute("create_item_message", institution.getName() + " cadastrada com sucesso!");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		return targetView;
	}

	@GetMapping(value = "/show/{id}")
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
