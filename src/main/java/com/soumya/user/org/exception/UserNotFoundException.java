package com.soumya.user.org.exception;

public class UserNotFoundException extends RuntimeException{
	
	public UserNotFoundException(String error) {
		
		super(error);
	}

}
