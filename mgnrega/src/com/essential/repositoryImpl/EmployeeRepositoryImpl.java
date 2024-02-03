package com.essential.repositoryImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.essential.dto.EmployeeUpdatedDto;
import com.essential.entites.Employee;
import com.essential.exceptions.EmployeeAlreadyExistExcepton;
import com.essential.exceptions.OperationFaliureException;
import com.essential.repository.EmployeeRepository;

public class EmployeeRepositoryImpl implements EmployeeRepository {
	
	private Connection conn;

	public EmployeeRepositoryImpl(Connection conn) {
		super();
		this.conn = conn;
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}
	
	private Employee getEmployeeByEmail(String email ,Connection conn) throws SQLException {
		Employee emp = new Employee();
		String query= "Select * from Employee where email=?";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setString(1, email);
		ResultSet rs= ps.executeQuery();
		if(isResultSetEmpty(rs))
			  return null;
		else
		    rs.next();
		
		emp= getEmployeeFromResultset(rs);
		if(!rs.isClosed())
			  rs.close();
		
		if(!ps.isClosed())
			ps.close();
		
		return emp;
	}

	private Employee getEmployeeFromResultset(ResultSet rs) throws SQLException {
		Employee emp =  new Employee();
		emp.setEmpId(rs.getString("empId"));
		emp.setFname(rs.getString("firstName"));
		emp.setLname(rs.getString("lastName"));
		emp.setEmail(rs.getString("email"));
		emp.setGender(rs.getString("gender"));
		emp.setPasword(rs.getString("password"));
		emp.setPosition(rs.getString("position"));
		emp.setDob( rs.getDate("dob").toLocalDate());
		emp.setCreatedAt(rs.getTimestamp("createdAt").toLocalDateTime());
		
		
		return emp;
	}
	
	private List<Employee> getListOfEmployee(ResultSet rs) throws SQLException
	{
		List<Employee> res= new ArrayList<>();
		while(rs.next())
		{
			Employee emp = getEmployeeFromResultset(rs);
			if(emp!=null)
			{
				res.add(emp);
			}
		}
		
		return res;
	}
	
	
	private boolean isResultSetEmpty(ResultSet rs) throws SQLException {
		if(rs==null)
			  return true;
		
		if(!rs.isBeforeFirst() && rs.getRow()==0)
			  return true;
		else
			return false;
	}
	@Override
	public Optional<Employee> addEmployee(Employee ob) throws SQLException,EmployeeAlreadyExistExcepton,OperationFaliureException{
		Employee ans=null;
		Employee tmp= getEmployeeByEmail(ob.getEmail(),conn);
		if(tmp!=null)
			  throw new EmployeeAlreadyExistExcepton("Employee already exist");
		
		try {
			conn.setAutoCommit(false);
			
			
			ob.setPosition("Worker");
			ob.setCreatedAt(LocalDateTime.now());
		
			String query= "insert into Employee  (empId,firstName,lastName,email,gender,password,position,createdAt,dob) values (?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps= conn.prepareStatement(query);
			ps.setString(1, ob.getEmpId());
			ps.setString(2, ob.getFname());
			ps.setString(3, ob.getLname());
			ps.setString(4, ob.getEmail());
			ps.setString(5, ob.getGender());
			ps.setString(6, ob.getPasword());
			ps.setString(7, ob.getPosition());
			ps.setTimestamp(8, Timestamp.valueOf(ob.getCreatedAt()) );
			ps.setDate(9, Date.valueOf(ob.getDob()));
			int res= ps.executeUpdate();
			if(!ps.isClosed())
				 ps.close();
			
			
			if(res>0)
			{
				ans= getEmployeeByEmail(ob.getEmail(),conn);
				conn.commit();
			}
			else
			{
			    throw new Exception("Something went wrong"); 	
			}
			
			
			
		}catch(Exception ex)
		{
			conn.rollback();
			 throw new OperationFaliureException(" unable to add the employee details");
		}
		
		
		
		return Optional.ofNullable(ans);
	}

	@Override
	public Optional<Employee> updateEmployeeDetails(Integer eid, EmployeeUpdatedDto ob) throws SQLException, OperationFaliureException {
		
		if(eid==null || ob==null)
			   throw new OperationFaliureException("Something went wrong with the operation or null values passed to operation");
		
		
		
		
		return Optional.empty();
	}

	@Override
	public Optional<Employee> removeEmployee(Integer eid) throws SQLException {
		
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
