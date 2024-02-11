package com.essential.repositoryImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.essential.dto.GpMemberUpdatedDto;
import com.essential.entites.GpMember;
import com.essential.exceptions.GpMemberNotFoundException;
import com.essential.exceptions.OperationFaliureException;
import com.essential.repository.GpMemberRepository;

public class GpMemberRepositoryImpl implements GpMemberRepository {
	
	private Connection conn;
	
	public GpMemberRepositoryImpl(Connection conn) {
		this.conn=conn;
	}
	
	
	protected boolean isResultSetEmpty(ResultSet rs) throws SQLException {
		
		if((rs==null) || (!rs.isBeforeFirst() && rs.getRow()==0))
			  return true;
		else
			return false;
	}
	
	protected List<GpMember> getListOfGpMember(ResultSet rs) throws OperationFaliureException, SQLException{
		
		if(isResultSetEmpty(rs))
		    throw new OperationFaliureException("Provided resultset contains no rows");
		
		List<GpMember> res=null;
		
		res= new ArrayList<>();
		while(rs.next())
		{
			GpMember gpmember= getGpMemberFromResultSet(rs);
			res.add(gpmember);
		}
		
		if(!rs.isClosed())
			  rs.close();
		
		
		return res;
	}
	
	
	protected GpMember getGpMemberFromResultSet(ResultSet rs) throws OperationFaliureException, SQLException
	{
		 if(isResultSetEmpty(rs))
			    throw new OperationFaliureException("Provided resultset contains no rows");
		
		GpMember res= null;
		
		if(rs.isBeforeFirst())
			  rs.next();
		
		res= new GpMember();
		res.setGpmMemberId(rs.getInt("gpmId"));
		res.setFirstName(rs.getString("firstName"));
		res.setLastName(rs.getString("lastName"));
		res.setEmail(rs.getString("email"));
		res.setGender(rs.getString("gender"));
		res.setDob(rs.getDate("dob").toLocalDate());
		res.setMobno(rs.getString("mobno"));
		res.setPassword(rs.getString("password"));
		res.setCreatedAt(rs.getTimestamp("createdAt").toLocalDateTime());
		
		
		if(res.getEmail()==null && res.getGpmMemberId()==null && res.getFirstName()==null)
			 throw new OperationFaliureException("GpMember details not found ");
		
		
		
		return res;
	}

	
	protected GpMember getGpMemberByEmail(String email,Connection conn) throws OperationFaliureException, SQLException {
		if(email==null)
			  throw new OperationFaliureException("Passed email is invalid or null");
		
		GpMember res=null;
		String query= "Select * from GpMember where email=? and isDeleted=0";
		PreparedStatement ps= conn.prepareStatement(query);
		ps.setString(1, email);
		
		ResultSet rs= ps.executeQuery();
		
		res= getGpMemberFromResultSet(rs);
		
		
		if(!rs.isClosed())
			  rs.close();
		
		
		return res;
	}
	@Override
	public Optional<GpMember> addGramPanchatyatMember(GpMember ob) throws SQLException, OperationFaliureException {
		GpMember res=null;
		
		if(ob==null)
			  throw new OperationFaliureException("Oops , Somthing went wrong and Null values passed in method");
		
		conn.setAutoCommit(false);;
		try {
			 
			
		 conn.commit();	
		}catch(Exception exp) {
			conn.rollback();
			System.out.println(exp.getMessage());
			throw new OperationFaliureException("Oops,Something went wrong or Operation falied");
		}
		
		conn.setAutoCommit(true);
		
		return Optional.ofNullable(res);
	}

	@Override
	public Optional<GpMember> updateGpMember(Integer gpid, GpMemberUpdatedDto ob) throws SQLException, OperationFaliureException {
		
		GpMember res=null;
		try {
			conn.setAutoCommit(false);
			
			conn.commit();
		}catch(Exception exp)
		{
			conn.rollback();
			throw new OperationFaliureException("Oops , Something went wrong ,Operation falied");
		}
		conn.setAutoCommit(true);
		
		return Optional.ofNullable(res);
	}

	@Override
	public Optional<GpMember> removeGpMember(Integer gpid) throws SQLException, OperationFaliureException, GpMemberNotFoundException {
		if(gpid==null)
			 throw new OperationFaliureException("GpMember id is invalid or null");
		
	    GpMember  gpmember= getGpMember(gpid).orElseThrow(()->new GpMemberNotFoundException("GpMember not found for id :"+gpid));
	    GpMember res=null;
	    
		try {
			conn.setAutoCommit(false);
			
			String query= "Update GpMember set isDeleted=1 where gpmId=?";
			PreparedStatement ps= conn.prepareStatement(query);
			ps.setInt(1, gpid);
			int tmp= ps.executeUpdate();
			
			if(tmp<=0)
				   throw new Exception("Unable to delete the GpMember with id :" +gpid);
			
		
			res=gpmember;
			
			conn.commit();
		}catch(Exception exp)
		{
			conn.rollback();
			throw new OperationFaliureException("Oops, Somthing went wrong ,Operation got falied");
		}
		
		conn.setAutoCommit(true);
		return Optional.ofNullable(res);
	}

	@Override
	public Optional<List<GpMember>> getAllGpMember() throws SQLException, OperationFaliureException {
		
		List<GpMember> res=null;
		String query= "Select * from GpMember where isDeleted=0";
		PreparedStatement ps= conn.prepareStatement(query);
		
		ResultSet rs= ps.executeQuery();
		res= getListOfGpMember(rs);
		
		return Optional.ofNullable(res);
		
	}

	@Override
	public Optional<GpMember> getGpMember(Integer gpid) throws SQLException, OperationFaliureException {
		if(gpid==null)
			  throw new OperationFaliureException("Passed id is invalid or null");
		
		GpMember res=null;
		String query= "Select * from GpMember where gpmId=? and isDeleted=0";
		PreparedStatement ps= conn.prepareStatement(query);
		ps.setInt(1,gpid);
		
		ResultSet rs= ps.executeQuery();
		
		res= getGpMemberFromResultSet(rs);
		
		
		if(!rs.isClosed())
			  rs.close();
		
		
		
		
		return Optional.ofNullable(res);
		
	}
	

}
