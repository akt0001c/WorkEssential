package com.masaischool.ui;
import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.masaischool.dao.BdoOperations;
import com.masaischool.dao.BdoOperationsImpl;
import com.masaischool.dto.GPMember;
import com.masaischool.dto.Project;
import com.masaischool.exceptions.NoEmployeeFoundException;
import com.masaischool.exceptions.NoMemberFoundException;
import com.masaischool.exceptions.NoProjectFoundException;


public class LoginIntoBdo {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	 static String user="BDO";
	 static String pass="BDO";
	 static int value=3;
	 
	 public static void createProject() throws IOException, SQLException {
		 BdoOperations bo= new BdoOperationsImpl();
		     System.out.println("Enter Project id , Project Name and Project stating date(yyyy-mm-dd) :");
		     String id= br.readLine();
		     String name= br.readLine();
		     LocalDate date= LocalDate.parse(br.readLine());
		    String msg= bo.createProject(id, name, date);
		    System.out.println(msg);
		     
	 }
	
	 
     public static void viewListProject()throws IOException, SQLException, NoProjectFoundException{
    	 BdoOperations bo= new BdoOperationsImpl();
    	List<Project>  list=  bo.viewListOfProjects();
    	for(Project pro:list)
    	{
    		if(pro.getGpm()==null)
    		 System.out.println("Project id ="+pro.getPid()+"  Project name = "+pro.getPname() +" Project Start Date = "+ pro.getStartDate());
    		else
    			 System.out.println(pro);
    	}
     }
     
     
    public static void createMember()throws IOException, SQLException 
    {
    	 BdoOperations bo= new BdoOperationsImpl();
    	 System.out.println("Enter GPM Name,gender ,age, joindate :");
    	 String name= br.readLine();
    	 String gender= br.readLine();
    	 int age= Integer.parseInt(br.readLine());
    	 String d= br.readLine();
    	
    	 LocalDate ob= LocalDate.parse(d);
    	 
    	 Date date= Date.valueOf(ob);
    	 
    	 System.out.println("Create username and password :");
    	 String user= br.readLine();
    	 String pass= br.readLine();
    	System.out.println( bo.createGPM(name, gender, age, date, user, pass));
    }

  
    
    public  static void viewAllGPMember()throws IOException , SQLException, NoMemberFoundException{
    	BdoOperations bo= new BdoOperationsImpl();
    	List<GPMember> list = bo.viewListOfGPM();
    	for(GPMember gp:list)
    	{
    		System.out.println(gp);
    	}
    }
   
    public static void getAllocation()throws IOException,SQLException, NoMemberFoundException{
    	 BdoOperations bo= new BdoOperationsImpl();
    	 System.out.println("Enter Project name and GP member name whom you want to assign the project:");
    	 String pname=br.readLine();
    	 String gpname= br.readLine();
    	 bo.getProjectAllocate(pname, gpname);
    }
    
    public static void getListOfEmployee()throws IOException ,SQLException, NoEmployeeFoundException, NoProjectFoundException{
    	BdoOperations bo= new BdoOperationsImpl();
    	System.out.println("Enter Project name:");
    	String pname=br.readLine();
    	bo.getList(pname);
    }
    
    public static void getOptions() throws NumberFormatException, IOException {
		int choice=0;
		do {
			
			System.out.println("=======================================================");
			System.out.println();
			System.out.println("1.Create a Project\n2.View LIst of Project\n3.Create New Gram Panchayat Member\n"
					+ "4.View All Gram Panchayat Members\n5.Allocate a particular project to GMP\n6.See List of Employees working on a particular project");
			System.out.println("0.For Exit");
			choice= Integer.parseInt(br.readLine());
			
			switch(choice) {
			case 0: System.out.println("Thank you for using our services");
				    break;
			case 1:
				  try {
					    createProject();
				  }catch(Exception ex) {System.out.println(ex);}
				  break;
			case 2:
				try {
				    viewListProject();
			  }catch(Exception ex) {System.out.println(ex);}
				  break;
			case 3:
				try {
				      createMember();
			  }catch(Exception ex) {System.out.println(ex);}
				  break;
			case 4:
				try {
				     viewAllGPMember();
			  }catch(Exception ex) {System.out.println(ex);}
				  break;
			case 5:
				try {
				      getAllocation();
			  }catch(Exception ex) {System.out.println(ex);}
				  break;
			case 6:
				try {
				      getListOfEmployee();
			  }catch(Exception ex) {System.out.println(ex);}
				  break;
			default:
				   System.out.println("Invalid choice ,please try again");
			}
			   
 	}while(choice!=0);
	}
	
	
	private static boolean isCredentialValid(String username,String password)
	{
		if(user.equals(username)&& pass.equals(password))
			  return true;
		else 
			return false;
	}
	
	public static void getInputValues()throws IOException {
		
		System.out.print("Enter User Name : ");
		String name= br.readLine();
		System.out.print("Enter password : ");
		String password= br.readLine();
		boolean checker= isCredentialValid(name,password);
		if(checker==true)
		{
			//code after login bdo portal
			System.out.println("Welcome in BDO portal");
			  getOptions();
			
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
