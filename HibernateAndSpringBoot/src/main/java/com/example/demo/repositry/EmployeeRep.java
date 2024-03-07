package com.example.demo.repositry;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Employee;

@Repository
public class EmployeeRep {
	
	@Autowired
	private EntityManager manager;
	
   public List<Employee> get(){	   
	   Session session =   manager.unwrap(Session.class);
	   Query<Employee> query =  session.createQuery("FROM Employee" ,Employee.class);	  
	   List<Employee > data = query.getResultList();	   
	   return data ;
   }
   
   public List<Employee> getEmployee(int id){	   
	   Session session =   manager.unwrap(Session.class);
	   Query<Employee> query =  session.createQuery("FROM Employee where id ="+id ,Employee.class);	  
	   List<Employee > data = query.getResultList();	   
	   return data ;
   }

}
