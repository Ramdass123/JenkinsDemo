package com.example.demo.webservice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)

public class NoSuchElementExceptioninoder extends RuntimeException {	
public NoSuchElementExceptioninoder() {		
		
	}
	
	public NoSuchElementExceptioninoder(String message) {
		
		super(message);
	}

}
