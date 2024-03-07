package com.example.demo.helloworld;

public class HelloBean {

	private String name;
	private String Dept;

	public HelloBean(String name, String Dept) {
		// TODO Auto-generated constructor stub
		
		this.name = name ;
		
		this.Dept = Dept ;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDept() {
		return Dept;
	}

	public void setDept(String dept) {
		Dept = dept;
	}
	
	
	

}
