package com.hive4bee.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	
	@GetMapping("/login")
	public String login(HttpServletRequest request, HttpServletResponse response) {
		String referer = request.getHeader("Referer");
		request.getSession().setAttribute("prePage", referer);
		return "/login";
	}
	
	@GetMapping("/logout")
	public void logout() {
		
	}
	
}
