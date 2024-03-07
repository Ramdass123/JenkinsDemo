package com.example.demo.webservice;


import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
public class UserBean {

	@Id
	@GeneratedValue
	private int id ;
	
	@Size(min = 2 ,message = "Name should be atleast more then two")
	//@JsonProperty("My_Name")
	private String name ;
	
	//@Size(min = 2 , message = "birthdate  should be atleast more then two" )
	//@JsonProperty("My_birth_date ")
	private Date birthdate ;
	
	
	@OneToMany(mappedBy = "userbean")
	@JsonIgnore
	private List<Posts> posts ;
	    

	public UserBean() {
		super();
	}

	public UserBean(int id, String name, Date birthdate) {
		super();
		this.id = id;
		this.name = name;
		this.birthdate = birthdate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	
	public List<Posts> getPosts() {
		return posts;
	}

	@Override
	public String toString() {
		return "UserBean [id=" + id + ", name=" + name + ", birthdate=" + birthdate + "]";
	}

	
}
