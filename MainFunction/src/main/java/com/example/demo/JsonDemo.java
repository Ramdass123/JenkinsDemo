package com.example.demo;

import org.json.JSONObject;
import com.google.gson.Gson;

public class JsonDemo {

	public String Demofuction() {

		JSONObject json = new JSONObject();
		json.put("name", "Kumar");
		json.put("id", "1234");
		json.put("dept", "MCA");

		System.out.println("json " + json);

		Gson gson = new Gson();

		Employee emp = new Employee();

		emp.setName("Ram");

		emp.setId(2);

		emp.setDept("MSC");

		String employee = gson.toJson(emp);

		Employee e1 = gson.fromJson(employee, Employee.class);

		return "Demo" + e1;

	}
}
