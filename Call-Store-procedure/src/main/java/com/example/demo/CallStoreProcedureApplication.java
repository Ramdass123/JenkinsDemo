package com.example.demo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CallStoreProcedureApplication {
	
	
	public static void main(String[] args) {
		
		new CallStoreProcedureApplication().CallStoreProcedure();
	}
	

	    public String  CallStoreProcedure() {
	    	
		Connection con = CallStoreProcedureApplication.createDataBaseConnection();
		
		 CallStoreProcedureApplication.firstStoreprocedurecall(con);
		 
		 System.out.println(" Success fully insert First Table Record ");
		// CallStoreProcedureApplication.secondStoreprocedurecall(con);
		 
		// System.out.println(" Success fully insert Second  Table Record ");
		 
		 return " Multi table insert Successfully" ;
			
		
	}

	
	public static Connection createDataBaseConnection() {
		Connection connection = null ;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			connection = DriverManager.getConnection("jdbc:sqlserver://192.168.1.57:1433;databaseName=Java_TrainingProject", "sa", "Pointel14") ;
			return connection ;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return connection ;
		
	}
	

	public  static String  firstStoreprocedurecall(Connection con) {
		
		try {
			CallableStatement cs = con.prepareCall("{call firstDemolambda (?,?,?)}");
			cs.setString(1, "Ram@123");
			cs.setString(2, "Ram");
			cs.setString(3, "1234");
		    cs.executeUpdate(); 
		    System.out.println("Insert the Data Success Fully firstStoreprocedurecall ");	
		} catch (SQLException e) {
			e.printStackTrace();
		}   
		return "Insert the Data Success Fully firstStoreprocedurecall";	
	}
	
	
	public  static String  secondStoreprocedurecall(Connection con) {
		try {
			CallableStatement cs = con.prepareCall("{call  Lambdaprocedure (?,?)}");
			cs.setString(1, "Ram@123");
			cs.setString(2, "Ram");
		    cs.executeUpdate(); 
		    System.out.println("Insert the Data Success Fully SecondStoreprocedurecall ");	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		    finally {
	        if(con != null) {
	            try {
	                con.close();
	            } catch (SQLException e) {
	                System.out.println(e);
	            }
	        }
	    }	
		return "Insert the Data Success Fully SecondStoreprocedurecall";
	}
		
}
