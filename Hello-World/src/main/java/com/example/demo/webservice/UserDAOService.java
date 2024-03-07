package com.example.demo.webservice;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.context.annotation.Configuration;

@Configuration
public class UserDAOService {
	
	private static List<UserBean> user = new ArrayList<> () ;
	
	static {
		
		user .add(new UserBean(1, "AAA" , new Date()));
		user .add(new UserBean(2, "BBB" , new Date()));
		user .add(new UserBean(3, "CCC" , new Date()));
	}

	
	public List<UserBean> findall() {
		
		return user;
	}
	
	public UserBean save(UserBean user1) {	
		user.add(user1);
		return user1;		  
		
	}

	public UserBean get(int id){
		
		Predicate<? super UserBean> predicate = name -> name.getId() == id;	
		return user.stream().filter(predicate).findFirst().orElse(null);
		
		
					
	}
	
	
	public boolean  deletebyId(int id) {
		
		Predicate<? super UserBean> predicate = name -> name.getId() == id;	
		
		UserBean userbean = user.stream().filter(predicate).findFirst().orElse(null); 
		
		 return user.remove(userbean);	
	}
		
}
