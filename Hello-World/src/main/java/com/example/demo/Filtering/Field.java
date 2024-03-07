package com.example.demo.Filtering;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;

@JsonFilter("fieldBean")
public class Field {

	
	private int id ;
	
	//@JsonIgnore 
	// static filter in values
	private String pasword ;
	
	private String name ;
	

	public Field(int id, String pasword, String name) {
		super();
		this.id = id;
		this.pasword = pasword;
		this.name = name;
	}


	public int getId() {
		return id;
	}


	public String getPasword() {
		return pasword;
	}


	public String getName() {
		return name;
	}


	@Override
	public String toString() {
		return "Field [id=" + id + ", pasword=" + pasword + ", name=" + name + "]";
	}
	
	
	

	
}
