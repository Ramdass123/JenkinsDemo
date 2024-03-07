package com.example.demo;

import org.json.JSONObject;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;

public class Demogson implements RequestHandler<Object, String> {

    @Override
    public String handleRequest(Object input, Context context) {
        context.getLogger().log("Input: " + input+"\n");
        
        JSONObject json = new JSONObject();
        json.put("name", "Kumar");
        json.put("id", "1234");
        json.put("dept", "MCA");
        
        context.getLogger().log("json " + json);
        
    	Gson gson = new Gson();
        
        Employee emp  = new Employee();
        
        emp.setName("Ram");
        
        emp.setId(2);
        
        emp.setDept("MSC");
        
        String employee = gson.toJson(emp);
        
        Employee e1  =  gson.fromJson(employee, Employee.class);
        
    	
    	
        // TODO: implement your handler
        return "Welcome to lambda code" + e1;
    }
}
