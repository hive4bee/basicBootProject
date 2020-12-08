package com.boot08.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import lombok.extern.java.Log;
@Log
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		List<String> roleNames = new ArrayList<>();
		
		authentication.getAuthorities().forEach(authority -> {
			
			roleNames.add(authority.getAuthority());
		});
		log.info("roleNames:" + roleNames);
		if(roleNames.contains("ROLE_MANAGER")) {
			response.sendRedirect("/manager");
		}else if(roleNames.contains("ROLE_ADMIN")) {
			response.sendRedirect("/admin");
		}
		
	}
}
