package com.soumya.user.org.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.soumya.user.org.DTO.APIException;
import com.soumya.user.org.exception.UserNameAlreadyExists;
import com.soumya.user.org.exception.UserNotFoundException;

@RestControllerAdvice
public class UserControllerAdvice {
	
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<APIException> methodArgumentNotValidException(MethodArgumentNotValidException exception){
		System.out.println("test");
				List<String> errors = exception.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
		//List<FieldError> fieldErrors = exception.getFieldErrors("getDefaultMessage");
		System.out.println(errors);
		System.out.println("test completed");
		
		APIException exceptionRespone=APIException.builder().errorType("MethodArgumentNotValidException").errorCode(400+"").errorDescription(errors.get(0)).build();
		
		return new ResponseEntity<APIException>(exceptionRespone,HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = UserNameAlreadyExists.class)
	public ResponseEntity<APIException> userNameAlreadyExists(UserNameAlreadyExists exception){
		
		APIException exceptionRespone=APIException.builder().errorType("UserNameAlreadyExists").errorCode(400+"").errorDescription(exception.getMessage()).build();
		
		return new ResponseEntity<APIException>(exceptionRespone,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = HttpMessageNotReadableException.class)
	public ResponseEntity<APIException> httpMessageNotReadableException(HttpMessageNotReadableException exception){
		
		APIException exceptionRespone=APIException.builder().errorType("HttpMessageNotReadableException").errorCode(400+"").errorDescription(exception.getMessage()).build();
		
		return new ResponseEntity<APIException>(exceptionRespone,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = UserNotFoundException.class)
	public ResponseEntity<APIException> userNotFoundException(UserNotFoundException exception){
		
		APIException exceptionRespone=APIException.builder().errorType("UserNotFoundException").errorCode(404+"").errorDescription(exception.getMessage()).build();
		
		return new ResponseEntity<APIException>(exceptionRespone,HttpStatus.BAD_REQUEST);
	}
}
