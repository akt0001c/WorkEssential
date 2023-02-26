package com.masaischool.dao;

import java.sql.SQLException;
import java.util.List;

import com.masaischool.dto.Employee;
import com.masaischool.exceptions.NoEmployeeFoundException;

public interface GPMOperation {
 public String createEmployee(String id, String name)throws SQLException;
 public List<Employee> viewDetailsOfEmployee() throws NoEmployeeFoundException,SQLException;
 public String assignToProjecet(String pname,String ename,int days,int wag)throws SQLException;
 public List<Employee> workDetails(String pname)throws SQLException,NoEmployeeFoundException;
}
