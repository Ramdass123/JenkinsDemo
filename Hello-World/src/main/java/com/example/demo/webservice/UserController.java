package com.example.demo.webservice;

import java.net.URI;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserController {

	
	MessageSource messageSource ;	
	public UserController(MessageSource messageSource ) {
		this.messageSource =  messageSource ;
	}
	
	
	@Autowired
	UserDAOService userdao ;
	
	@GetMapping(value = "getList" )
	public List<UserBean>  getListofUser(){
		
		
		return userdao.findall() ;
	}
	
	@PostMapping("saveuser")
	public ResponseEntity<UserBean> Saveuser(@Valid @RequestBody UserBean user) {
		
		System.out.println("USer Bean" + user);
		UserBean user1  =  userdao.save(user) ;
	     
	    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
	    		.path("/{id}").buildAndExpand(user1.getId()).toUri();
		return ResponseEntity.created(location).build()	;
	}
    @GetMapping("getid/{id}")
	  public UserBean particularuser(@PathVariable  int id ) {	 
    	UserBean user =  userdao.get(id) ;		
		  if(user == null) 
			 throw new NoSuchElementExceptioninoder(" id " + id );		  
		  return user ;	
   
	 }	 
    @DeleteMapping("/delete/{id}")
    public String deleted(@PathVariable int id ) {
    	
    	 boolean  value = userdao.deletebyId(id);
    	 if(value) 
    		 return "Success" ;    	 
    	 else
    		throw new NoSuchElementExceptioninoder() ;
    
    }  
   @GetMapping("/internatinalization")
   public String i18N() {	   
	   Locale locale = LocaleContextHolder.getLocale();
	  return  messageSource.getMessage("hello.good", null, "Default Message", locale);
	   
   }
}
