package com.example.demo.Filtering;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.deser.impl.FieldProperty;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {
	

	@GetMapping("/get")
	public String name() {
		
		return "Welcome to micro service" ;
	}
	
	
	
	
	
	
	
	
	
	@GetMapping("Field")
	public Field field() {
		
		return  new Field(1,"PASSWOR","HEllo") ;
				
	}
	
	
	// Dynamic Filter in Bean Class 
	@GetMapping("staticfilter")
	public MappingJacksonValue staticfield() {
		
		Field filed =   new Field(1,"PASSWOR","HEllo") ;
		MappingJacksonValue  mappingJacksonValue = new MappingJacksonValue(filed);	
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id","pasword");
		SimpleFilterProvider field = new SimpleFilterProvider().addFilter("fieldBean", filter);
		mappingJacksonValue.setFilters(field);
		return mappingJacksonValue ;
				
	}
}
