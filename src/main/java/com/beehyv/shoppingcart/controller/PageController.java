package com.beehyv.shoppingcart.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
	@RequestMapping("/home")
	public String home() {
		return "homepage.html";
	}
	@RequestMapping("/")
	public String ss() {
		return "homepage.html";
	}
	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	@RequestMapping("/profile")
	public String profile()
	{
		return "profile.html";
	}
	@PreAuthorize("hasAnyRole('USER')")
	@RequestMapping("/cart")
	public String cart()
	{
		return "cart.html";
	}
	@PreAuthorize("hasAnyRole('USER')")
	@RequestMapping("/orderHistory")
	public String orderHistory()
	{
		return "order.html";
	}
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping("/adminPage")
	public String adminPage()
	{
		return "admin.html";
	}
	@RequestMapping("/login")
	public String loginPage()
	{
		return "login.html";
	}

}

