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

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BdoRepositoryImpl implements BdoRepository {
	
	private Connection conn;
	
	public BdoRepositoryImpl(Connection conn)
	{
		this.conn=conn;
	}
	
	private Bdo getBdoFromResultSet(ResultSet rs) throws SQLException {
		
		Bdo res= new Bdo();
		if(rs.next())
		{
		
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
		}
		
		if(!rs.isClosed())
		     rs.close();
		
		
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
		
		if(!rs.isClosed())
			  rs.close();
		return ans;
	}
	
	private boolean isResultSetEmpty(ResultSet rs) throws SQLException{
		if(rs==null)
			  return true;
		
		if(!rs.isBeforeFirst() && rs.getRow()==0)
			 return true;
		else
			return false;
	}
	
	public ResultSet getBdoResultSet(String email,Connection conn) throws SQLException  {
		 ResultSet resultset=null;
		 
		String tmp_query = "select * from Bdo where email=? and isDeleted=0";
		PreparedStatement ps1= conn.prepareStatement(tmp_query);
		ps1.setString(1, email);
		resultset = ps1.executeQuery();
		
		return resultset;
	}
	
	private Bdo getBdoByEmail(String email,Connection conn) throws SQLException, OperationFaliureException
	{
		Bdo res=null;
		ResultSet resultset = getBdoResultSet(email,conn);
		boolean flag= isResultSetEmpty(resultset);
		
		if( flag==true)
			 throw new OperationFaliureException("Bdo not found");
		
		res= getBdoFromResultSet(resultset);
		
		return res;
	}

	@Override
	public Optional<Bdo> addBdo(Bdo ob) throws SQLException, OperationFaliureException  {
		 Bdo ans= null;
		
		
		try {
			conn.setAutoCommit(false);
			
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
				
				ans= getBdoByEmail(ob.getEmail(),conn);
				 conn.commit();
			}
			else
				 throw new Exception("Unable to add the Bdo details"); 
		}
		 catch(Exception ex) {
			 conn.rollback();
			 System.out.println(ex.getMessage());
		     throw new OperationFaliureException("Something went wrong with the process ,Please try again");
		     
		 }
		
		
		return Optional.ofNullable(ans);
	}

	@Override
	public Optional<Bdo> updateBdoDetails(Integer bid, BdoUpdatedDto ob) throws SQLException, BdoNotFoundException, OperationFaliureException {
		if(bid==null || ob==null)
			  throw new OperationFaliureException("Nullable parameter passed ,Please try again with valid parameters");
		
		
		 Bdo res= null;
		try {
			conn.setAutoCommit(false);
			
			
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
		
		res= getBdo(bid).orElseThrow(()->new BdoNotFoundException("Bdo not found for id : "+ bid+""));
		
		conn.commit();
		
		
		}
		 catch(Exception ex)
		{
			 conn.rollback();
			 System.out.println(ex.getMessage());
			 throw new OperationFaliureException("Something went wrong with the operation");
			 
		}
		
		
	
		
		return Optional.ofNullable(res);
	}

	@Override
	public Optional<Bdo> removeBdo(Integer bid) throws SQLException, BdoNotFoundException, OperationFaliureException {
	  Bdo res= getBdo(bid).orElseThrow(()->new BdoNotFoundException("Bdo not found for id : "+ bid+" "));
	 
	  try {
		   conn.setAutoCommit(false);
		   String query= "update Bdo SET isDeleted=1 where bdoId=?";
		   PreparedStatement ps= conn.prepareStatement(query);
		   ps.setInt(1, bid);
		   int tmp = ps.executeUpdate();
		   if(tmp<=0)
			    throw new Exception("Unable to delete Bdo details with bdo id : "+bid+" ");
		   
		  conn.commit();
	  }catch(Exception ex) {
		  conn.rollback();
		  System.out.println(ex.getMessage());
		  throw new OperationFaliureException("Something went wrong");
	  }
	    
	    
	 
		return Optional.ofNullable(res);
	}

	@Override
	public Optional<List<Bdo>> getAllBdo() throws SQLException, OperationFaliureException {
		 List<Bdo> res=null;
		try {
			
			String query = "Select * from Bdo where isDeleted=0";
			PreparedStatement ps= conn.prepareStatement(query);
			ResultSet rs= ps.executeQuery();
			
			if(isResultSetEmpty(rs))
				 throw new BdoNotFoundException("Bdo list is empty");
			
			res= getBdoList(rs);
			if(res.isEmpty())
				  throw new BdoNotFoundException("No Bdo Found");
			
			
		}catch(Exception ex) {
			System.out.println(ex);
			throw new OperationFaliureException("Something went wrong with the operation");
		}
		
		return Optional.ofNullable(res);
	}

	@Override
	public Optional<Bdo> getBdo(Integer bid) throws SQLException, OperationFaliureException {
		 Bdo res=null;
		try {
			
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
		
	
		
		return Optional.ofNullable(res);
	}

}
