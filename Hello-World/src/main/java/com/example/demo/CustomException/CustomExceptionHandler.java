package com.example.demo.CustomException;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.demo.webservice.NoSuchElementExceptioninoder;

@RestController
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(NoSuchElementExceptioninoder.class)
	public final ResponseEntity<Object> customeException(Exception ex, WebRequest request) throws Exception {

		CustomResponse custome = new CustomResponse(new Date(), ex.getMessage(), request.getDescription(false));

		return new ResponseEntity<Object>(custome, HttpStatus.NOT_FOUND);
	}
	 
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

    CustomResponse custome = new CustomResponse(new Date() , ex.getFieldError().getDefaultMessage(), request.getDescription(false));
		
    return  new  ResponseEntity<Object>(custome, HttpStatus.BAD_REQUEST) ;
	
	}
	
	
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
			HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		
		 CustomResponse custome = new CustomResponse(new Date() , ex.getMessage(), request.getDescription(false));
			
		    return  new  ResponseEntity<Object>(custome, HttpStatus.BAD_REQUEST) ;
		
	}
	
	
	
}
