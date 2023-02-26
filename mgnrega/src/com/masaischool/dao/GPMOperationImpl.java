package com.masaischool.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.masaischool.dto.Employee;
import com.masaischool.dto.EmployeeImpl;
import com.masaischool.exceptions.NoEmployeeFoundException;


public class GPMOperationImpl implements GPMOperation {

	@Override
	public String createEmployee(String id,String name) throws SQLException {
	    String res=null; Connection conn=null;
	    try {
			 conn= GetConnection.connectToDatabase();
			 
			 String query= "INSERT INTO employee(empId,empName) values(?,?)";
			 PreparedStatement ps= conn.prepareStatement(query);
			 ps.setString(1, id);
			 ps.setString(2, name);
			
			 
			res= ps.executeUpdate()>0?"Employee has been  added ":"Employee  couldn't added";
			 
		  }
		   catch(SQLException  ex){ System.out.println("unable to connect with server");}
		    finally {
		    	GetConnection.closeConnection(conn);
		    }
		return res;
	}
	
	private List<Employee> getList(ResultSet rs) throws SQLException {
		List<Employee> list = new ArrayList<>();
		while(rs.next())
		{
			Employee emp= new EmployeeImpl();
			String id= rs.getString("empId");
			String name= rs.getString("empName");
			emp.setEmpId(id);
			emp.setEmpName(name);
		    list.add(emp);
		}
		
		return list;
	}
	 private boolean isSetEmpty(ResultSet rs) throws SQLException{
		  if(!rs.isBeforeFirst() && rs.getRow()==0)
			    return true;
		   else
		      return false;
	   }
	 

	@Override
	public List<Employee> viewDetailsOfEmployee() throws NoEmployeeFoundException, SQLException {
		
		List<Employee> list= null;
		  Connection conn=null;
		  try {
			 conn= GetConnection.connectToDatabase();
			 String query= "Select * from Employee ";
			 PreparedStatement ps= conn.prepareStatement(query);
			 
			 ResultSet rs= ps.executeQuery();
			 if(isSetEmpty(rs))
				   throw new NoEmployeeFoundException("No Employee Found");
			
			list= getList(rs); 
			
			 
		  }
		   catch(SQLException  ex){ System.out.println("unable to connect with server");}
		    finally {
		    	GetConnection.closeConnection(conn);
		    }
		return list;
	}
	

	@Override
	public String assignToProjecet(String pname,String ename,int days,int wag) throws SQLException {
	      Connection conn=null; String res=null;
		
		 try {
			 conn= GetConnection.connectToDatabase();
			 
			 String query= "UPDATE employee SET projectName=?,No_of_days=?,wages=? where empName=?";
			 PreparedStatement ps= conn.prepareStatement(query);
			 ps.setString(1, pname);
			 ps.setInt(2, days);
			 ps.setInt(3, wag);
			 ps.setString(4, ename);
			
			 
			res= ps.executeUpdate()>0?"Project has been allocated ":"Something went wrong";
			 
		  }
		   catch(SQLException  ex){ System.out.println("unable to connect with server");}
		    finally {
		    	GetConnection.closeConnection(conn);
		    }

		return res;
	}
	

	private List<Employee> getWagesDetails(ResultSet rs) throws SQLException {
		List<Employee> list = new ArrayList<>();
		while(rs.next())
		{
			Employee emp= new EmployeeImpl();
			String id= rs.getString("empId");
			String name= rs.getString("empName");
			int ndays= rs.getInt("No_of_days");
			int wag= rs.getInt("wages");
			emp.setEmpId(id);
			emp.setEmpName(name);
			emp.setNo_of_days(ndays);
			emp.setWages(wag);
			
		    list.add(emp);
		}
		
		return list;
	}
	@Override
	 public List<Employee> workDetails(String pname)throws SQLException,NoEmployeeFoundException {
		
		List<Employee> list= null;
		  Connection conn=null;
		  try {
			 conn= GetConnection.connectToDatabase();
			 String query= "Select * from Employee where projectName=?";
			 PreparedStatement ps= conn.prepareStatement(query);
			 ps.setString(1, pname);
			 ResultSet rs= ps.executeQuery();
			 if(isSetEmpty(rs))
				   throw new NoEmployeeFoundException("No Employee Found");
			
			list= getWagesDetails(rs); 
			
			 
		  }
		   catch(SQLException  ex){ System.out.println("unable to connect with server");}
		    finally {
		    	GetConnection.closeConnection(conn);
		    }
		return list;
	}

}
