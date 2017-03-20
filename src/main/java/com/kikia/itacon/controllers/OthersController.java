package com.kikia.itacon.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OthersController {

	@RequestMapping("/undefined")
	public String getUndefinedFunctionality() {
		return "undefined";
	}
}
