package com.example.demo.helloworld;

import java.time.LocalDate;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello_world {
	
	
	@RequestMapping(method = RequestMethod.GET ,path = "/hello/{name}")
	public String helloworld(@PathVariable ("name")String name) {
		return "Hello World  "  + name;
	}

	@GetMapping("helloBean")
	public HelloBean hellobean() {
		 return new HelloBean("Ram" , "Msc") ;
	}
	 
	
	public static void main(String[] args) {
		
		LocalDate local = null  ;
		System.out.println(local.now().minusYears(25));
	}
	
}
