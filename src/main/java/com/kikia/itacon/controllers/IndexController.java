package com.kikia.itacon.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping("/")
	String index() {	
		return "index";
	}
	
	@RequestMapping(value="/dashboard")
	String actLogin() {
		return "dashboard";
	}
}