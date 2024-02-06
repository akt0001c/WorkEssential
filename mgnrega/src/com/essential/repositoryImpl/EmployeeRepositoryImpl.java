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
import com.essential.exceptions.EmployeeListEmptyException;
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
		String query= "Select * from Employee where email=? and isDeleted=0";
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
		emp.setMobno(rs.getString("mobno"));
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
		
			String query= "insert into Employee  (empId,firstName,lastName,email,gender,password,position,createdAt,dob,mobno) values (?,?,?,?,?,?,?,?,?)";
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
			ps.setString(10, ob.getMobno());
			
			
			int res= ps.executeUpdate();
			if(!ps.isClosed())
				 ps.close();
			
			
			if(res>0)
			{
				ans= getEmployeeByEmail(ob.getEmail(),conn);
				conn.commit();
				conn.setAutoCommit(true);
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
	public Optional<Employee> updateEmployeeDetails(Integer eno, EmployeeUpdatedDto ob) throws SQLException, OperationFaliureException {
		
		if(eno==null || ob==null)
			   throw new OperationFaliureException("Something went wrong with the operation or null values passed to operation");
		
		Employee emp= getEmployeeByEmpno(eno).orElseThrow(()-> new OperationFaliureException("Invalid emp  id number passed : "+ eno));
		
	   
		
		try {	
		   conn.setAutoCommit(false);
		
		if(ob.getEmail()!=null)
		{
			String query= "Update Employee SET email=? where empno=? and isDeleted=0 ";
			PreparedStatement ps= conn.prepareStatement(query);
			ps.setString(1, ob.getEmail());
			ps.setInt(2, eno);
			int tmp= ps.executeUpdate();
			
			if(!ps.isClosed())
				 ps.close();
			
			if(tmp<=0)
				  throw new  Exception("Something went wrong with email updation");
		}
		
		if(ob.getFname()!=null)
		{
			String query= "Update Employee SET firstName=? where empno=? and isDeleted=0 ";
			PreparedStatement ps= conn.prepareStatement(query);
			ps.setString(1,ob.getFname() );
			ps.setInt(2, eno);
			int tmp= ps.executeUpdate();
			
			if(!ps.isClosed())
				 ps.close();
			if(tmp<=0)
				  throw new  Exception("Something went wrong with First Name updation");
		}
		
		if(ob.getLname()!=null)
		{
			String query= "Update Employee SET lastName=? where empno=? and isDeleted=0 ";
			PreparedStatement ps= conn.prepareStatement(query);
			ps.setString(1, ob.getLname());
			ps.setInt(2, eno);
			int tmp= ps.executeUpdate();
			
			if(!ps.isClosed())
				 ps.close();
			if(tmp<=0)
				  throw new  Exception("Something went wrong with Last Name updation");
		}
		
		if(ob.getMobno()!=null)
		{
			String query= "Update Employee SET mobno=? where empno=? and isDeleted=0 ";
			PreparedStatement ps= conn.prepareStatement(query);
			ps.setString(1, ob.getMobno());
			ps.setInt(2, eno);
			int tmp= ps.executeUpdate();
			
			if(!ps.isClosed())
				 ps.close();
			if(tmp<=0)
				  throw new  Exception("Something went wrong with employee mobile number updation");
		}
		
		if(ob.getPosition()!=null)
		{
			String query= "Update Employee SET position=? where empno=? and isDeleted=0 ";
			PreparedStatement ps= conn.prepareStatement(query);
			ps.setString(1, ob.getPosition());
			ps.setInt(2, eno);
			int tmp= ps.executeUpdate();
			
			if(!ps.isClosed())
				 ps.close();
			if(tmp<=0)
				  throw new  Exception("Something went wrong with  position  updation");
		}
		
		 emp= getEmployeeByEmpno(eno).get();
		 conn.commit();
		 conn.setAutoCommit(true);
		
		
		
		
	   }catch(Exception ex) {
		 conn.rollback();
		 throw new OperationFaliureException("Error encountred while operation process and Unable to process updation process");
	   }
		
		
		
		return Optional.ofNullable(emp);
	}

	@Override
	public Optional<Employee> removeEmployee(Integer eno) throws SQLException,OperationFaliureException {
		
	    if(eno==null)
	    	    throw new OperationFaliureException(" null employee number passed");
	    
	    Employee emp= getEmployeeByEmpno(eno).orElseThrow(()-> new OperationFaliureException("Employee not exist with privided employee no :"+eno));
	    
	    try {
	    	conn.setAutoCommit(false);
	    String query= "Update Employee set isDeleted=1 where empno=? and isDeleted=0";
	    PreparedStatement ps = conn.prepareStatement(query);
	    ps.setInt(1, emp.getEmpno());
	    int tmp= ps.executeUpdate();
	    
	    if(!ps.isClosed())
	    	    ps.close();
	    
	    if(tmp<=0)
	    	 throw new Exception(" Oops ,Something went wrong");
	    
	    conn.commit();
	    conn.setAutoCommit(true);
	    
	    }catch(Exception exp) {
	    	conn.rollback();
	    	throw new OperationFaliureException("Deletion got falied");
	    }
	    
		
		return Optional.ofNullable(emp);
	}

	@Override
	public Optional<List<Employee>> getAllEmployees() throws SQLException ,EmployeeListEmptyException {
		List<Employee> res=null;
		String query="select * from Employee where isDeleted=0 ";
		PreparedStatement ps= conn.prepareStatement(query);
		
		ResultSet rs= ps.executeQuery();
		if(isResultSetEmpty(rs))
			  throw new EmployeeListEmptyException("Employee list is empty");
		
		
		res= getListOfEmployee(rs);
		
		if(!rs.isClosed())
			rs.close();
		
		if(!ps.isClosed())
			 ps.close();
		
		if(res.isEmpty())
			  throw new EmployeeListEmptyException("Employee list found empty");
		
		
		
		return Optional.ofNullable(res);
	}

	@Override
	public Optional<Employee> getEmployeeDetails(String eid) throws SQLException {
		
		return Optional.empty();
	}

	@Override
	public Optional<Employee> getEmployeeByEmpno(Integer eno) throws SQLException, OperationFaliureException {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

}
