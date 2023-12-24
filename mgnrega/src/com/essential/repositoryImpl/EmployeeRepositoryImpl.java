package com.essential.repositoryImpl;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.essential.dto.EmployeeUpdatedDto;
import com.essential.entites.Employee;
import com.essential.repository.EmployeeRepository;

public class EmployeeRepositoryImpl implements EmployeeRepository {

	@Override
	public Optional<Employee> addEmployee(Employee ob) throws SQLException {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<Employee> updateEmployeeDetails(Integer eid, EmployeeUpdatedDto ob) throws SQLException {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<Employee> removeEmployee(Integer eid) throws SQLException {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<List<Employee>> getAllEmployees() throws SQLException {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<Employee> getEmployeeDetails(Integer eid) throws SQLException {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

}
