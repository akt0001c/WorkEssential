package com.essential.repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.essential.dto.EmployeeUpdatedDto;
import com.essential.entites.Employee;
import com.essential.exceptions.EmployeeAlreadyExistExcepton;
import com.essential.exceptions.EmployeeListEmptyException;
import com.essential.exceptions.NoEmployeeFoundException;
import com.essential.exceptions.OperationFaliureException;

public interface EmployeeRepository {
	public  Optional<Employee>  addEmployee(Employee ob) throws SQLException,EmployeeAlreadyExistExcepton, OperationFaliureException;
	  public  Optional<Employee>  updateEmployeeDetails(Integer eid,EmployeeUpdatedDto ob)  throws SQLException, OperationFaliureException, NoEmployeeFoundException;
	  public  Optional<Employee>  removeEmployee(Integer eid)  throws SQLException, OperationFaliureException, NoEmployeeFoundException;
	  public  Optional<List<Employee>>  getAllEmployees()  throws SQLException, EmployeeListEmptyException;
	  public  Optional<Employee>  getEmployeeDetails(String eid)  throws SQLException, NoEmployeeFoundException, OperationFaliureException;
	  public Optional<Employee>  getEmployeeByEmpno(Integer eno)throws SQLException,OperationFaliureException, NoEmployeeFoundException;
}
