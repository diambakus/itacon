package com.kikia.itacon.controllers.individuo;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kikia.itacon.converter.BigDecimalConverter;
import com.kikia.itacon.domain.Individuo;
import com.kikia.itacon.domain.User;
import com.kikia.itacon.services.IndividuoService;
import com.kikia.itacon.services.UserService;

@Controller
@RequestMapping("/individuo")
public class IndividuoController {

	private final Logger logger = LoggerFactory.getLogger(IndividuoController.class);

	private IndividuoService individuoService;
	private UserService userService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(BigDecimal.class, new BigDecimalConverter());
	}

	@Autowired
	public IndividuoController(IndividuoService individuoService, UserService userService) {
		this.individuoService = individuoService;
		this.userService = userService;
	}

	@GetMapping(value = "/individuos")
	public String allIndividuos(Model model, Principal principal) {
		String viewValue = null;
		User user = userService.findByUsername(principal.getName());
		model.addAttribute("individuo", new Individuo());
		if (user != null) {
			model.addAttribute("user", user);
			model.addAttribute("individuos", individuoService.listAllIndividuos());
			viewValue = "individuo/individuos";
		} else {
			viewValue = "unexpected_error";
		}
		return viewValue;
	}

	@RequestMapping("/searchindividuo")
	public String searchIndividuo(Model model) {
		model.addAttribute("individuos", individuoService.listAllIndividuos());
		return "searchindividuo";
	}

	@GetMapping(value = "info/{id}")
	public String show(@PathVariable("id") Long id, Model model, Principal principal) {
		String view = "unexpected_error";
		logger.debug("showIndividuo()");
		User user = userService.findByUsername(principal.getName());

		if (user != null) {
			view = "individuo/individuo";
			model.addAttribute("user", user);
			Individuo individuo = individuoService.getIndividuoById(id);
			model.addAttribute("individuo", individuo);
		}

		return view;
	}

	@RequestMapping("individuo/edit/{Id}")
	public String edit(@PathVariable("Id") Long id, Model model) {
		model.addAttribute("individuo", individuoService.getIndividuoById(id));
		return "editindividuo";
	}

	@PostMapping(value = "/updatebalance/{id}")
	public String updateBalance(@RequestParam(value = "rechargeValue", required = false) BigDecimal rechargeValue,
			@PathVariable("id") Long id, Model model, @ModelAttribute("Individuo") Individuo individuo) {

		Individuo persistedIndividuo = individuoService.getIndividuoById(id);
		BigDecimal balance = persistedIndividuo.getBalance();
		BigDecimal result = balance.add(rechargeValue);
		persistedIndividuo.setBalance(result);
		individuoService.saveIndividuo(persistedIndividuo);

		return "redirect:/" + "individuo/info/" + id;
	}

	@GetMapping(value = "/register")
	public String createIndividuo(Model model, Principal principal) {
		logger.debug("createNewIndividuo()");
		User user = userService.findByUsername(principal.getName());
		String viewValue = null;
		if (user != null) {
			model.addAttribute("individuo", new Individuo());
			model.addAttribute("user", user);
			viewValue = "individuo/individuos";
		} else {
			viewValue = "unexpected_error";
		}
		return viewValue;
	}

	/*
	 * TODO after insert an user it should show table of user with option to
	 * update credit
	 */
	@PostMapping(value = "/register")
	public String saveNewIndividuo(@ModelAttribute("individuo") Individuo individuo, Model model, Principal principal) {

		logger.debug("saveProduct()");
		User user = userService.findByUsername(principal.getName());
		String viewValue = null;
		if (user != null) {
			model.addAttribute("user", user);
			if (individuo != null)
				individuoService.saveIndividuo(individuo);
			viewValue = "redirect:/individuo/individuos";
		} else {
			viewValue = "unexpected_error";
		}
		return viewValue;
	}

	@GetMapping("delete/{id}")
	public String delete(@PathVariable("id") Long id, Model model, Principal principal) {
		String viewValue = "redirect:/individuo/individuos";
		User user = userService.findByUsername(principal.getName());
		if (user != null) {
			individuoService.deleteIndividuo(id);
			model.addAttribute("user", user);
			model.addAttribute("individuos", individuoService.listAllIndividuos());
		} else {
			viewValue = "unexpected_error";
		}
		return viewValue;
	}
}
