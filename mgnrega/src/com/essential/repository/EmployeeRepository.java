package com.essential.repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.essential.dto.EmployeeUpdatedDto;
import com.essential.entites.Employee;

public interface EmployeeRepository {
	public  Optional<Employee>  addEmployee(Employee ob) throws SQLException;
	  public  Optional<Employee>  updateEmployeeDetails(Integer eid,EmployeeUpdatedDto ob)  throws SQLException;
	  public  Optional<Employee>  removeEmployee(Integer eid)  throws SQLException;
	  public  Optional<List<Employee>>  getAllEmployees()  throws SQLException;
	  public  Optional<Employee>  getEmployeeDetails(Integer eid)  throws SQLException;
}
