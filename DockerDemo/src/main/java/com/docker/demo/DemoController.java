package com.docker.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

	
	Logger logger  = LoggerFactory.getLogger(DemoController.class);
	
	@GetMapping("/Welome")
	public String getname() {
		
		logger.info("This is Start application fine") ;
		
		return "My Application start Fine " ;
		
	}
}
