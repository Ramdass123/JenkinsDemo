package com.example.demo.webservice;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Posts {

	

	@Id
	@GeneratedValue
	private int id ;
	
	@Size(min=10)
	private String description ;
	
	
	

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private UserBean userbean;
	
	
	
	public int getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		return "Posts [id=" + id + ", Description=" + description + "]";
	}

	public UserBean getUserbean() {
		return userbean;
	}

	public void setUserbean(UserBean userbean) {
		this.userbean = userbean;
	}
	
	
}
