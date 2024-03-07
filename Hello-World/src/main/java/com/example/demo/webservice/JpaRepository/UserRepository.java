package com.example.demo.webservice.JpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.webservice.UserBean;

public interface UserRepository  extends JpaRepository<UserBean, Integer>{
	

}
