package com.example.demo.webservice.JpaController;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.webservice.NoSuchElementExceptioninoder;
import com.example.demo.webservice.Posts;
import com.example.demo.webservice.UserBean;
import com.example.demo.webservice.UserDAOService;
import com.example.demo.webservice.JpaRepository.PostRepository;
import com.example.demo.webservice.JpaRepository.UserRepository;

@RestController
public class JpaController {

	
	UserRepository  repository  ;
	
	PostRepository  postRepository;
	
	public JpaController( UserRepository repository,PostRepository  postRepository) {
		super();
		this.repository = repository;
		this.postRepository = postRepository ;
	}




	@GetMapping(value = "/jpa/getList" )
	public List<UserBean>  getListofUser(){
		
		
		return repository.findAll() ;
	}
	
	
	@GetMapping("/jpa/getid/{id}")
	  public UserBean particularuser(@PathVariable  int id ) {	 
  	Optional<UserBean> user =  repository.findById(id);		
		  if(user.isEmpty()) 
			 throw new NoSuchElementExceptioninoder(" id " + id );
		  
		  return user.get() ;	
 
	 }
	
	 @DeleteMapping("/jpa/delete/{id}")
	    public void deleted(@PathVariable int id ) {
	    	
	    	  repository.deleteById(id);
				/*
				 * if(value) return "Success" ; else throw new NoSuchElementExceptioninoder() ;
				 */
	    }
	 
	 
	 @PostMapping("/jpa/saveuser")
		public ResponseEntity<UserBean> Saveuser(@Valid @RequestBody UserBean user) {
			
			System.out.println("USer Bean" + user);
			UserBean user1  =  repository.save(user) ;
		     
		    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
		    		.path("/{id}").buildAndExpand(user1.getId()).toUri();
			return ResponseEntity.created(location).build()	;
		}
	 
	 @GetMapping("/jpa/get/{id}/posts")
	 public List<Posts> Getpost(@PathVariable int id ){
		 
			Optional<UserBean> user =  repository.findById(id);		
			  if(user.isEmpty()) 
				 throw new NoSuchElementExceptioninoder(" id " + id );
			  
			  return user.get().getPosts() ;	
		 
	 }
		
	 @PostMapping("/jpa/post/{id}/posts")
	 public ResponseEntity<Posts> Getpost(@PathVariable int id , @Valid @RequestBody Posts post){
		 
			Optional<UserBean> user =  repository.findById(id);		
			  if(user.isEmpty()) 
				 throw new NoSuchElementExceptioninoder(" id " + id );
		
			  
			 
			  post.setUserbean(user.get());
			  postRepository.save(post);
			  
			  URI location = ServletUriComponentsBuilder.fromCurrentRequest()
			    		.path("/{id}").buildAndExpand(post.getId()).toUri();
				return ResponseEntity.created(location).build()	;	
		 
	 }
	 
}
