package com.boot09.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boot09.domain.Member;
import com.boot09.service.MemberService;

import lombok.extern.java.Log;

@Log
@Controller
@RequestMapping("/member/*")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("/join")
	public void join() {
		
	}
	
	@PostMapping("/join")
	public String joinPost(@ModelAttribute("member") Member member) {
		log.info("Member: " + member);
		
		memberService.joinMember(member);
		
		return "/member/joinResult";
	}
	
}
