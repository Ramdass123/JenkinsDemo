package com.example.demo.springprofiles;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Springprofile {

	@Value("${spring.name}")
   private  String name ; 
	
   @Bean
	public String name() {
		
	   System.out.println(name);
	   return name ;
	}
	
	
	
}
