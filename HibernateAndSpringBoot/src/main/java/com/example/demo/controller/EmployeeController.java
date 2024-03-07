package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.Employee;
import com.example.demo.repositry.EmployeeRep;

@RestController
public class EmployeeController {
	
	@Autowired
	EmployeeRep emp ;

	@GetMapping("/microservice")
	public String getname() {
		
		RestTemplate rest = new RestTemplate();
		
		return rest.getForObject("http://localhost:8081/get", String.class);
		
	}
	

	@GetMapping("/GetEmployee")
	public List<Employee> get(){
		
		List<Employee>  value = emp.get();
		
		return value ;
	} 
	
	@GetMapping("/Employee/{id}")
     public List<Employee> getEmployee(@PathVariable("id") int id){
	
		System.out.println("id = " + id);
		List<Employee>  value = emp.getEmployee(id);
		
		return value ;
	} 
	
}
