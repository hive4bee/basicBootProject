package com.boot09.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.boot09.domain.Member;
import com.boot09.service.MemberService;

import lombok.extern.java.Log;

@Controller
@Log
public class LoginController {
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("/login")
	public String login(HttpServletRequest request, HttpServletResponse response) {
		//HttpServletRequest request, HttpServletResponse response
		String referer = request.getHeader("Referer");
		request.getSession().setAttribute("prePage", referer);
		return "/login";
	}
	
	//deprecated
	@PostMapping("/login")
	public ResponseEntity<String> loginPro(@RequestBody Member vo){
		log.info("=====================");
		log.info("=====================");
		log.info("vo : " + vo);
		log.info("=====================");
		log.info("=====================");
		return memberService.loginPro(vo) == 1 ?
				new ResponseEntity<>("success", HttpStatus.OK) :
					new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("/accessDenied")
	public void accessDenied() {
		
	}
	
	@GetMapping("/logout")
	public void logout() {
		
	}
	
}
