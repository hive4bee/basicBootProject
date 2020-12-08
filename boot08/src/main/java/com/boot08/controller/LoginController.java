package com.boot08.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.boot08.domain.Member;
import com.boot08.service.MemberService;

import lombok.extern.java.Log;

@Controller
@Log
public class LoginController {
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("/login")
	public void login() {
		
	}
	
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
