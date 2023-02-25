package com.masaischool.ui;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class LoginIntoBdo {
	 static String user="BDO";
	 static String pass="BDO";
	 static int value=3;
	
	public static boolean isCredentialValid(String username,String password)
	{
		if(user.equals(username)&& pass.equals(password))
			  return true;
		else 
			return false;
	}
	
	public static void getInputValues()throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Enter User Name : ");
		String name= br.readLine();
		System.out.print("Enter password : ");
		String password= br.readLine();
		boolean checker= isCredentialValid(name,password);
		if(checker==true)
		{
			//code after login bdo portal
			System.out.println("Welcome in bdo portal");
		}
		else
		{
			value--;
		 
		  if(value>0)
		  {
			  System.out.println("Invalid Credential  ,you have left "+value+" attempt, try again");
			  getInputValues();
		  }
		  else
		  {
			  System.out.println("Invalid Credential and you have used all attempt , Thank you for using our services");
		  }
		   
		}
		br.close();
	}
}
