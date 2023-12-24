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

import com.essential.dto.BdoUpdatedDto;
import com.essential.entites.Bdo;
import com.essential.exceptions.BdoNotFoundException;
import com.essential.exceptions.OperationFaliureException;
import com.essential.repository.BdoRepository;
import com.essential.utility.MyServerStarter;

public class BdoRepositoryImpl implements BdoRepository {
	
	private Bdo getBdoFromResultSet(ResultSet rs) throws SQLException {
		Bdo res= new Bdo();
		res.setBdoId(rs.getInt("bdoId"));
		res.setFirstName(rs.getString("firstName"));
		res.setLastName(rs.getString("lastName"));
		LocalDateTime datetimestamp= rs.getTimestamp("createdAt").toLocalDateTime();
		res.setCreatedAt(datetimestamp);
		res.setDesignation(rs.getString("designation"));
		res.setEmail(rs.getString("email"));
		res.setMobno(rs.getString("mobno"));
		res.setDob(rs.getDate("dob").toLocalDate());
		res.setPassword(rs.getString("password"));
		res.setIsDeleted(rs.getInt("isDeleted"));
		
		
		return res;
	}
	
	private List<Bdo> getBdoList(ResultSet rs)throws SQLException{
		List<Bdo> ans= new ArrayList<>();
		while(rs.next())
		{
			Bdo res= new Bdo();
			res.setBdoId(rs.getInt("bdoId"));
			res.setFirstName(rs.getString("firstName"));
			res.setLastName(rs.getString("lastName"));
			LocalDateTime datetimestamp= rs.getTimestamp("createdAt").toLocalDateTime();
			res.setCreatedAt(datetimestamp);
			res.setDesignation(rs.getString("designation"));
			res.setEmail(rs.getString("email"));
			res.setMobno(rs.getString("mobno"));
			res.setDob(rs.getDate("dob").toLocalDate());
			res.setPassword(rs.getString("password"));
			res.setIsDeleted(rs.getInt("isDeleted"));
			
			ans.add(res);
			
		}
		return ans;
	}
	
	private boolean isResultSetEmpty(ResultSet rs) throws SQLException{
		if(rs==null)
			  return true;
		
		return !rs.next();
	}
	
	public ResultSet getBdoResultSet(String email) throws SQLException  {
		Connection conn1=null; ResultSet resultset=null;
		
		conn1= MyServerStarter.connectToDatabase();
		
		String tmp_query = "select * from Bdo where email=? and isDeleted=0";
		PreparedStatement ps1= conn1.prepareStatement(tmp_query);
		ps1.setString(1, email);
		resultset = ps1.executeQuery();
		MyServerStarter.closeConnection(conn1);
		return resultset;
	}
	
	private Bdo getBdoByEmail(String email) throws SQLException, OperationFaliureException
	{
		Bdo res=null;
		ResultSet resultset = getBdoResultSet(email);
		boolean flag= isResultSetEmpty(resultset);
		
		if( flag==true)
			 throw new OperationFaliureException("Bdo not found");
		
		res= getBdoFromResultSet(resultset);
		
		return res;
	}

	@Override
	public Optional<Bdo> addBdo(Bdo ob) throws SQLException, OperationFaliureException  {
		Connection conn=null; Bdo ans= null;
		
		
		try {
			conn= MyServerStarter.connectToDatabase();
			String query = "Insert into Bdo (email,mobno,firstName,lastName,dob,designation,password,createdAt) values (?,?,?,?,?,?,?,?)";
			PreparedStatement ps= conn.prepareStatement(query);
			ps.setString(1, ob.getEmail());
			ps.setString(2, ob.getMobno());
			ps.setString(3, ob.getFirstName());
			ps.setString(4, ob.getLastName());
			ps.setDate(5,Date.valueOf(ob.getDob()) );
			ps.setString(6, "Block Development Officer");
			ps.setString(7, ob.getPassword());
			ps.setTimestamp(8, Timestamp.valueOf(LocalDateTime.now()));
			
			int res= ps.executeUpdate();
			if(res>0)
			{
				
				ans= getBdoByEmail(ob.getEmail());
			}
			else
				 throw new Exception("Unable to add the Bdo details"); 
		}
		 catch(Exception ex) {
			 System.out.println(ex.getMessage());
		     throw new OperationFaliureException("Something went wrong with the process ,Please try again");
		     
		 }
		
		finally {
			MyServerStarter.closeConnection(conn);
		}
		return Optional.ofNullable(ans);
	}

	@Override
	public Optional<Bdo> updateBdoDetails(Integer bid, BdoUpdatedDto ob) throws SQLException, BdoNotFoundException, OperationFaliureException {
		Connection conn= null; Bdo res= null;
		try {
			res= getBdo(bid).orElseThrow(()->new BdoNotFoundException("Bdo not found for id : "+ bid+""));
			conn= MyServerStarter.connectToDatabase();
		if(ob.getEmail()!=null)
		{
			String query= "Update Bdo SET email=? where bdoId=?";
			PreparedStatement ps= conn.prepareStatement(query);
			ps.setString(1, ob.getEmail());
			ps.setInt(2,bid);
			int tmp=ps.executeUpdate();
			if(tmp<=0)
				 throw new Exception("Something went wrong with email updation due to invalid Bdo id");
		}
		
		if(ob.getFirstName()!=null)
		{
			String query= "Update Bdo SET firstName=? where bdoId=?";
			PreparedStatement ps= conn.prepareStatement(query);
			ps.setString(1, ob.getFirstName());
			ps.setInt(2,bid);
			int tmp=ps.executeUpdate();
			if(tmp<=0)
				 throw new Exception("Something went wrong with FirstName updation due to invalid Bdo id");
		}
		
		if(ob.getLastName()!=null)
		{
			String query= "Update Bdo SET lastName=? where bdoId=?";
			PreparedStatement ps= conn.prepareStatement(query);
			ps.setString(1, ob.getLastName());
			ps.setInt(2,bid);
			int tmp=ps.executeUpdate();
			if(tmp<=0)
				 throw new Exception("Something went wrong with LastName updation due to invalid Bdo id");
		}
		
		
		
		
		}
		 catch(Exception ex)
		{
			 System.out.println(ex.getMessage());
			 throw new OperationFaliureException("Something went wrong with the operation");
			 
		}
		
		
		
		finally {
			MyServerStarter.closeConnection(conn);
		}
		
		return Optional.ofNullable(res);
	}

	@Override
	public Optional<Bdo> removeBdo(Integer bid) throws SQLException, BdoNotFoundException, OperationFaliureException {
	  Bdo res= getBdo(bid).orElseThrow(()->new BdoNotFoundException("Bdo not found for id : "+ bid+" "));
	  Connection conn= null;
	  try {
		   conn= MyServerStarter.connectToDatabase();
		   String query= "update Bdo SET isDeleted=1 where bdoId=?";
		   PreparedStatement ps= conn.prepareStatement(query);
		   ps.setInt(1, bid);
		   int tmp = ps.executeUpdate();
		   if(tmp<=0)
			    throw new Exception("Unable to delete Bdo details with bdo id : "+bid+" ");
	  }catch(Exception ex) {
		  System.out.println(ex.getMessage());
		  throw new OperationFaliureException("Something went wrong");
	  }
	  
	  finally {
		  MyServerStarter.closeConnection(conn);
	  }
		return Optional.ofNullable(res);
	}

	@Override
	public Optional<List<Bdo>> getAllBdo() throws SQLException, OperationFaliureException {
		Connection conn=null; List<Bdo> res= new ArrayList<>();
		try {
			conn= MyServerStarter.connectToDatabase();
			String query = "Select * from Bdo where isDeleted=0";
			PreparedStatement ps= conn.prepareStatement(query);
			ResultSet rs= ps.executeQuery();
			
			res= getBdoList(rs);
			if(res.isEmpty())
				  throw new Exception("No Bdo Found");
			
			
		}catch(Exception ex) {
			System.out.println(ex);
			throw new OperationFaliureException("Something went wrong with the operation");
		}
		
		finally {
			MyServerStarter.closeConnection(conn);
		}
		return Optional.ofNullable(res);
	}

	@Override
	public Optional<Bdo> getBdo(Integer bid) throws SQLException, OperationFaliureException {
		Connection conn=null; Bdo res=null;
		try {
			conn= MyServerStarter.connectToDatabase();
			String query = "Select * from Bdo where bdoId=? and isDeleted=0";
			PreparedStatement ps= conn.prepareStatement(query);
			ps.setInt(1, bid);
			ResultSet rs= ps.executeQuery();
			
			boolean flag= isResultSetEmpty(rs);
			
			if( flag==true)
				 throw new Exception("Bdo not found with id :"+bid+" ");
			
			res= getBdoFromResultSet(rs);
			
			
			
		}catch(Exception ex) {
			System.out.println(ex);
			throw new OperationFaliureException("Something went wrong with the operation");
		}
		
		finally {
			MyServerStarter.closeConnection(conn);
		}
		
		return Optional.ofNullable(res);
	}

}
