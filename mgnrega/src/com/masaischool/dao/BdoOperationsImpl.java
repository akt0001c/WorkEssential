package com.masaischool.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.masaischool.dto.Employee;
import com.masaischool.dto.EmployeeImpl;
import com.masaischool.dto.GPMember;
import com.masaischool.dto.GPMemberImpl;
import com.masaischool.dto.Project;
import com.masaischool.dto.ProjectImpl;
import com.masaischool.exceptions.NoEmployeeFoundException;
import com.masaischool.exceptions.NoMemberFoundException;
import com.masaischool.exceptions.NoProjectFoundException;

public class BdoOperationsImpl implements BdoOperations {
	
	
	@Override
	public String createProject(String id,String pname,LocalDate date) throws SQLException {
	  Connection conn=null;String res="";
	  try {
		 conn= GetConnection.connectToDatabase();
		 
		 String query= "INSERT INTO projects(pid,pname,startDate) values(?,?,?)";
		 PreparedStatement ps= conn.prepareStatement(query);
		 ps.setString(1, id);
		 ps.setString(2, pname);
		 ps.setDate(3, Date.valueOf(date));
		 
		res= ps.executeUpdate()>0?"Project Added successfully":"Project couldn't added";
		 
	  }
	   catch(SQLException  ex){ System.out.println("unable to connect with server");}
	    finally {
	    	GetConnection.closeConnection(conn);
	    }
		return res;
	}
	
	private List<Project> getList(ResultSet rs) throws SQLException {
		List<Project> list = new ArrayList<>();
		while(rs.next())
		{
			Project proj= new ProjectImpl();
			proj.setPname(rs.getString("pname"));
			proj.setPid(rs.getString("pid"));
			Date dt= rs.getDate("startDate");
		    LocalDate date=	new java.sql.Date(dt.getTime()).toLocalDate();
		    proj.setStartDate(date);
		    
		    list.add(proj);
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
	public List<Project> viewListOfProjects() throws NoProjectFoundException ,SQLException {
		List<Project> list= null;
		  Connection conn=null;
		  try {
			 conn= GetConnection.connectToDatabase();
			 String query= "Select * from projects ";
			 PreparedStatement ps= conn.prepareStatement(query);
			 
			 ResultSet rs= ps.executeQuery();
			 if(isSetEmpty(rs))
				   throw new NoProjectFoundException("No Projects Found");
			
			list= getList(rs); 
			
			 
		  }
		   catch(SQLException  ex){ System.out.println("unable to connect with server");}
		    finally {
		    	GetConnection.closeConnection(conn);
		    }
		return list;
	}

	
	
	@Override
	public String createGPM(String name,String gender,int age,Date joindate,String username,String password) throws SQLException {
		Connection conn=null;String res="";
		  try {
			 conn= GetConnection.connectToDatabase();
			 
			 String query= "INSERT INTO gdmuseres (name,gender,age,joindate,username,password) values(?,?,?,?,?,?)";
			 PreparedStatement ps= conn.prepareStatement(query);
			 
			 ps.setString(1, name);
			 ps.setString(2, gender);
			 ps.setInt(3, age);
			 ps.setDate(4, joindate);
			 ps.setString(5, username);
			 ps.setString(6, password);
			 
		
			 
			res= ps.executeUpdate()>0?"Gram Panchayat Member added successfully":"Member  couldn't added";
			 
		  }
		   catch(SQLException  ex){ System.out.println("unable to connect with server");}
		    finally {
		    	GetConnection.closeConnection(conn);
		    }
		
		return res;
	}
	
	
	
	private List<GPMember> getGpmList(ResultSet rs) throws SQLException {
		List<GPMember> list = new ArrayList<>();
		while(rs.next())
		{
		    GPMember ob= new GPMemberImpl();
		    ob.setMname(rs.getString("name"));
		    ob.setAge(rs.getInt("age"));
		    ob.setGender(rs.getString("gender"));
		    LocalDate date=	new java.sql.Date(rs.getDate("joinDate").getTime()).toLocalDate();
		    ob.setJoinDate(date);
		    
		    ob.setUsername(rs.getString("username"));
		    ob.setPassword(rs.getString("password"));
		    
		    
		    list.add(ob);
		}
		
		return list;
	}

	
	
	@Override
	public List<GPMember> viewListOfGPM() throws NoMemberFoundException,SQLException {
		List<GPMember> list= null;
		
		
		  Connection conn=null;
		  try {
			 conn= GetConnection.connectToDatabase();
			 String query= "Select * from gdmuseres ";
			 PreparedStatement ps= conn.prepareStatement(query);
			 
			 ResultSet rs= ps.executeQuery();
			 if(isSetEmpty(rs))
				   throw new NoMemberFoundException("Gram Panchayat members list is empty");
			
			list= getGpmList(rs); 
			
			 
		  }
		   catch(SQLException  ex){ System.out.println("unable to connect with server");}
		    finally {
		    	GetConnection.closeConnection(conn);
		    }
		return list;
	}
	
	private int getMemberId(String Mname)throws SQLException, NoMemberFoundException
	{
		int id=0;
		 Connection conn=null;
		  try {
			 conn= GetConnection.connectToDatabase();
			 String query= "Select * from gdmuseres where name=?";
			 PreparedStatement ps= conn.prepareStatement(query);
			 ps.setString(1, Mname);
			 
			 ResultSet rs= ps.executeQuery();
			 if(isSetEmpty(rs))
				   throw new NoMemberFoundException("Member not found");
			
		   id= rs.getInt("gdmId");
			
			 
		  }
		   catch(SQLException  ex){ System.out.println("unable to connect with server");}
		    finally {
		    	GetConnection.closeConnection(conn);
		    }
		
		return id;
		
	}

	
	
	@Override
	public String getProjectAllocate(String pname,String Mname) throws SQLException, NoMemberFoundException {
		int id= getMemberId(Mname);
		
		Connection conn=null;String res="";
		  try {
			 conn= GetConnection.connectToDatabase();
			 
			 String query= "UPDATE projects SET AssignTo=? where pname=?";
			 PreparedStatement ps= conn.prepareStatement(query);
			 ps.setInt(1, id);
			 ps.setString(2, pname);
		
		
			 
			res= ps.executeUpdate()>0?"Project has been allocted":"Something went wrong";
			 
		  }
		   catch(SQLException  ex){ System.out.println("unable to connect with server");}
		    finally {
		    	GetConnection.closeConnection(conn);
		    }
		
		
		
		return res;
	}
	
	
	
	private Project getProject(String name) throws NoProjectFoundException, SQLException {
		Project proj= null;
		
		 Connection conn=null; 
		  try {
			 conn= GetConnection.connectToDatabase();
			 String query= "Select * from projects where  pname=?";
			 PreparedStatement ps= conn.prepareStatement(query);
			 ps.setString(1, name);
			 
			 ResultSet rs= ps.executeQuery();
			 
			 if(isSetEmpty(rs))
				   throw new NoProjectFoundException("Project not found");
			
		     proj = new ProjectImpl();
		     proj.setPid(rs.getString("pid"));
		     proj.setPname(rs.getString("pname"));
		     LocalDate date=	new java.sql.Date(rs.getDate("startDate").getTime()).toLocalDate(); 
		     
		     proj.setStartDate(date);
			
			 
		  }
		   catch(SQLException  ex){ System.out.println("unable to connect with server");}
		    finally {
		    	GetConnection.closeConnection(conn);
		    }
		return proj;
	}
	
	private List<Employee>  getEmpList(ResultSet rs) throws SQLException, NoProjectFoundException {
		List<Employee> list = new ArrayList<>();
		while(rs.next())
		{
		    Employee ob = new EmployeeImpl();
		    String empId= rs.getString("empId");
		    String  ename= rs.getString("empName");
		    int ndays= rs.getInt("No_of_days");
		    int wag= rs.getInt("wages");
		    String pname= rs.getString("projectName");
		    Project proj= getProject(pname);
		    ob.setEmpId(empId);
            ob.setEmpName(ename);
            ob.setProject(proj);
            ob.setNo_of_days(ndays);
            ob.setWages(wag);
		    
		    list.add(ob);
		}
		
		return list;
	}
	
	

	@Override
	public List<Employee> getList(String pname) throws NoEmployeeFoundException, SQLException, NoProjectFoundException {
		 Connection conn=null; List<Employee> list=null;
		  try {
			 conn= GetConnection.connectToDatabase();
			 String query= "Select * from Employee where  projectName=?";
			 PreparedStatement ps= conn.prepareStatement(query);
			 ps.setString(1, pname);
			 
			 ResultSet rs= ps.executeQuery();
			 
			 if(isSetEmpty(rs))
				   throw new NoEmployeeFoundException("Employee not found for this project");
			
		     list= getEmpList(rs);
			
			 
		  }
		   catch(SQLException  ex){ System.out.println("unable to connect with server");}
		    finally {
		    	GetConnection.closeConnection(conn);
		    }
		return list;
	}

}
