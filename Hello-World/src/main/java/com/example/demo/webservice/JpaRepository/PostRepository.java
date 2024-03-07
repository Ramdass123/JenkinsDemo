package com.example.demo.webservice.JpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.webservice.Posts;


public interface PostRepository  extends JpaRepository<Posts, Integer>{
	

}
