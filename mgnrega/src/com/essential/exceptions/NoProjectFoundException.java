package com.essential.exceptions;

public class NoProjectFoundException extends Exception {

	
	public NoProjectFoundException(String message) {
		super(message);
		
	}

	@Override
	public String toString() {
		return "NO Project found in database";
	}
	
	

	

}
