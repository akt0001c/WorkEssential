package com.masaischool.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.masaischool.dto.Employee;
import com.masaischool.dto.GPMember;
import com.masaischool.dto.Project;
import com.masaischool.exceptions.NoEmployeeFoundException;
import com.masaischool.exceptions.NoMemberFoundException;
import com.masaischool.exceptions.NoProjectFoundException;

public interface BdoOperations {

	public String createProject(String pid,String name,LocalDate date)throws SQLException;
	
	public List<Project> viewListOfProjects()throws NoProjectFoundException,SQLException;

	public List<GPMember> viewListOfGPM() throws NoMemberFoundException, SQLException;
			
	public List<Employee> getList(String pname) throws NoEmployeeFoundException,SQLException, NoProjectFoundException;
	
	public String createGPM(String name, String gender, int age, Date joindate, String username, String password)
			throws SQLException;

	public String getProjectAllocate(String pname, String Mname) throws  SQLException, NoMemberFoundException;
}
