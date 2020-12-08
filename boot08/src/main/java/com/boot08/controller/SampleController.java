package com.boot08.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.java.Log;

@Controller
@Log
public class SampleController {
	
	@GetMapping("/")
	public String index() {
		log.info("index");
		return "index";
	}
	
	@RequestMapping(value="/guest", method=RequestMethod.GET)
	public void forGuest() {
		log.info("guest");
	}
	
	@RequestMapping(value="/manager", method=RequestMethod.GET)
	public void forManager() {
		log.info("manager");
	}
	
	@RequestMapping(value="/admin", method=RequestMethod.GET)
	public void forAdmin() {
		log.info("admin");
	}
	
}
