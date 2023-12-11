package com.essential.exceptions;

public class NoEmployeeFoundException extends Exception {
  
	public NoEmployeeFoundException(String msg){
		super(msg);
	}

	@Override
	public String toString() {
		return "Unable to find the employee";
	}
	
	
}
