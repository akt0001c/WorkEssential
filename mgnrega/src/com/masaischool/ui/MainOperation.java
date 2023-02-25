package com.masaischool.ui;

import java.io.IOException;
import java.util.Scanner;

public class MainOperation {

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		System.out.println("Welcome in work essential");
		System.out.println("Enter your login choice:\n1.Login as BDO\n2.Login as Gram Panchyat Member");
		
		int choice=0;
		choice= scn.nextInt();
		switch(choice)
		{
		case 1 :try {
			    LoginIntoBdo.getInputValues();
		}catch(IOException ex) { System.out.println("Invalid Entry");}
			    break;
		case 2: System.out.println("Welcome in gpm portal");
			    break;
		default:
			    System.out.println("Invalid Entry");
		}
		 
      scn.close();
	}

}
