package com.essential.repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.essential.dto.EmployeeUpdatedDto;
import com.essential.entites.Employee;
import com.essential.exceptions.EmployeeAlreadyExistExcepton;
import com.essential.exceptions.OperationFaliureException;

public interface EmployeeRepository {
	public  Optional<Employee>  addEmployee(Employee ob) throws SQLException,EmployeeAlreadyExistExcepton, OperationFaliureException;
	  public  Optional<Employee>  updateEmployeeDetails(Integer eid,EmployeeUpdatedDto ob)  throws SQLException, OperationFaliureException;
	  public  Optional<Employee>  removeEmployee(Integer eid)  throws SQLException, OperationFaliureException;
	  public  Optional<List<Employee>>  getAllEmployees()  throws SQLException;
	  public  Optional<Employee>  getEmployeeDetails(Integer eid)  throws SQLException;
	  public Optional<Employee>  getEmployeeByEmpno(Integer eno)throws SQLException,OperationFaliureException;
}
