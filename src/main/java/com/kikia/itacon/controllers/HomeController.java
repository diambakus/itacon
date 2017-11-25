package com.kikia.itacon.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * System first screen
 * @author diambakus
 *
 */
@Controller
public class HomeController {

	@GetMapping(value = { "/", "/welcome" })
	String index(Model model) {
		return "index";
	}
}