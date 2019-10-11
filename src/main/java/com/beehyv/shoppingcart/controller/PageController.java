package com.beehyv.shoppingcart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
	@RequestMapping("/home")
	public String loginPage() {
		return "homepage.html";
	}

	@RequestMapping("/user-home")
	public String user() {
		return "homepage.html";
	}

	@RequestMapping("/logout-success")
	public String logout() {
		return "homepage.html";

	}
}

