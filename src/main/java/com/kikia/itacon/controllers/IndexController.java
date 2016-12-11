package com.kikia.itacon.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

	@RequestMapping("/")
	String index() {	
		return "index";
	}
	
	@RequestMapping(value="/dashboard", method=RequestMethod.GET)
	String actLogin() {
		return "dashboard";
	}
}
