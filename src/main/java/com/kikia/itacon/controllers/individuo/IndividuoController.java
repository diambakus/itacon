package com.kikia.itacon.controllers.individuo;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

	@GetMapping("/searchindividuo/{queriedindividuo}")
	public String findIndividuo(@PathVariable("queriedindividuo") String identity, Model model) {

		boolean found = false;
		Individuo individuo, targetIndividuo = null;
		Iterable<Individuo> allIndividuos = individuoService.listAllIndividuos();

		for (Iterator<Individuo> iterator = allIndividuos.iterator(); iterator.hasNext() && (!found);) {
			individuo = iterator.next();
			if (individuo.getBI().equalsIgnoreCase(identity)) {
				targetIndividuo = individuo;
				found = true;
			}
		}

		model.addAttribute("individuos", targetIndividuo);
		return "individuos :: individuos";
	}

	@RequestMapping("individuo/{Id}")
	public String showIndividuo(@PathVariable("Id") Long id, Model model) {
		logger.debug("showIndividuo()");
		model.addAttribute("individuo", individuoService.getIndividuoById(id));
		return "individuo";
	}

	@RequestMapping("individuo/edit/{Id}")
	public String edit(@PathVariable("Id") Long id, Model model) {
		model.addAttribute("individuo", individuoService.getIndividuoById(id));
		return "editindividuo";
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

	@RequestMapping("individuo/updatebalace/{Id}")
	public String showUpdateBalanceForm(@PathVariable("Id") Long Id, Model model) {

		logger.debug("showUpdateBalanceForm()");
		Individuo individuo = individuoService.getIndividuoById(Id);
		model.addAttribute("individuo", individuo);

		return "individuoupdatebalance";
	}

	@PostMapping(value = "individuo/{Id}/updatebalance")
	public String updateBalance(@RequestParam("recharge_amount") BigDecimal balance, @PathVariable("Id") Long Id,
			Model model) {

		Individuo individuo = individuoService.getIndividuoById(Id);

		logger.debug("updateBalance()");
		individuoService.saveIndividuo(individuo);
		model.addAttribute("individuos", individuoService.listAllIndividuos());

		return "redirect:/dashboard";
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

	@RequestMapping("individuo/delete/{Id}")
	public String delete(@PathVariable("Id") Long id) {
		individuoService.deleteIndividuo(id);
		return "redirect:/individuos";
	}
}
