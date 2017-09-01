package com.kikia.itacon.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.kikia.itacon.dto.UserDTO;

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