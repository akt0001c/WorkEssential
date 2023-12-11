package com.essential.exceptions;

public class NoMemberFoundException extends Exception {

 public NoMemberFoundException(String msg) {
	 super(msg);
 }

@Override
public String toString() {
	return "Unable to find any member";
}
 
 
}
