package com.essential.repository;

import java.util.List;
import java.util.Optional;

import com.essential.dto.EmployeeUpdatedDto;
import com.essential.entites.Employee;

public interface EmployeeRepository {
	public  Optional<Employee>  addEmployee(Employee ob);
	  public  Optional<Employee>  updateEmployeeDetails(Integer eid,EmployeeUpdatedDto ob);
	  public  Optional<Employee>  removeEmployee(Integer eid);
	  public  Optional<List<Employee>>  getAllEmployees();
	  public  Optional<Employee>  getEmployeeDetails(Integer eid);
}
