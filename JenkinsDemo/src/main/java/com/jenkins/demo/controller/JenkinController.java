package com.jenkins.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class JenkinController {
	
	
	@GetMapping("/name")
	public String name() {
		
		
		return "Ramachandran" ;
	}
	

}
