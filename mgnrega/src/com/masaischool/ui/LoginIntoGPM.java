package com.masaischool.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;

import com.masaischool.dao.GPMOperation;
import com.masaischool.dao.GPMOperationImpl;
import com.masaischool.dto.Employee;
import com.masaischool.exceptions.NoEmployeeFoundException;

public class LoginIntoGPM {
 static BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
 
 
 
 public static void getInput() throws IOException, SQLException {
	 GPMOperation gp = new GPMOperationImpl();
	 System.out.print("Enter UserName :");
	 String user= br.readLine();
	 System.out.print("Enter password :");
	 String pass= br.readLine();
	 
	 if(gp.checkCredendial(user,pass))
	 {
		getOptions(); 
	 }
	 else
	 {
		 System.out.println("Invaild Credential , Please try again");
		 getInput();
	 }
	 
	 
	 br.close();
 }
 
 private static void createEmployee()throws IOException ,SQLException {
	System.out.println("Enter Employee id and Name");
	String id=br.readLine();
	String name= br.readLine();
	GPMOperation gp = new GPMOperationImpl();
	System.out.println(gp.createEmployee(id, name));
 }
 
 private static void viewListEmployee() throws IOException ,SQLException, NoEmployeeFoundException{
	 GPMOperation gp = new GPMOperationImpl();
	 List<Employee> list= gp.viewDetailsOfEmployee();
	 for(Employee emp:list)
	 {
		 System.out.println(emp);
	 }
 }
 

 private static void assignEmployee()throws IOException ,SQLException
 {
	 System.out.println("Enter Employee name and Project name in which you want to assign employee : ");
	 String empname= br.readLine();
	 String pname= br.readLine();
	 System.out.println("Enter number of day for project and wage for this employee");
	 int ndays=Integer.parseInt(br.readLine());
	 int wage= Integer.parseInt(br.readLine());
	 GPMOperation gp = new GPMOperationImpl();
	 System.out.println(gp.assignToProjecet(empname, pname, ndays, wage));
	 
 }
 
 private static void viewDetailsForProjects() throws IOException ,SQLException, NoEmployeeFoundException{
	 System.out.println("Enter Project name which you want to see the Employees :");
	 String pname= br.readLine();
	 GPMOperation gp = new GPMOperationImpl();
	 List<Employee> list = gp.workDetails(pname);
	 for(Employee emp:list)
		  System.out.println("Employee Name = "+emp.getEmpName()+" Employee id= "+emp.getEmpId()+" Work in days = "+emp.getNo_of_days()+" Total wages ="+emp.getWages()+"\n");
 }
 
 
 
 public static void getOptions() throws NumberFormatException, IOException {
	 int choice=0;
	
	 do {
		 
		 System.out.println("===================================");
		 System.out.println();
	 System.out.println("1.Create Employee\n2.View the list of the Employees\n3.Assign Employee to a Project\n"
	 		+ "5.View total number of days Employee worked in a project \n6.Enter 0 for exit");
	 choice= Integer.parseInt(br.readLine());
	 switch(choice) {
	 case 0:
		   System.out.println("Thank for using our serveices");
		   break;
	 case 1:try {
		       createEmployee();
	           }catch(Exception ex) {System.out.println(ex);}
	           break;
	 case 2:try {
	       viewListEmployee();
            }catch(Exception ex) {System.out.println(ex);}
	      break;
	 case 3:try {
	         assignEmployee();
          }catch(Exception ex) {System.out.println(ex);}
	      break;
	 case 4:try {
            viewDetailsForProjects();
      }catch(Exception ex) {System.out.println(ex);}
      break;
	 default:
		   System.out.println("Invalid Choice");
	   

	 }
	 }while(choice!=0);
 }
}
