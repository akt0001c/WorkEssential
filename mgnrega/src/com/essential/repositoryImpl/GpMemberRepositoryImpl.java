package com.essential.repositoryImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.essential.dto.GpMemberUpdatedDto;
import com.essential.entites.GpMember;
import com.essential.exceptions.OperationFaliureException;
import com.essential.repository.GpMemberRepository;

public class GpMemberRepositoryImpl implements GpMemberRepository {
	
	private Connection conn;
	
	public GpMemberRepositoryImpl(Connection conn) {
		this.conn=conn;
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
	public Optional<GpMember> removeGpMember(Integer gpid) throws SQLException, OperationFaliureException {
		GpMember res=null;
		try {
			conn.setAutoCommit(false);
			
			
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
	public Optional<List<GpMember>> getAllGpMember() throws SQLException {
		
		return Optional.empty();
	}

	@Override
	public Optional<GpMember> getGpMember(Integer gpid) throws SQLException {
		
		return Optional.empty();
	}

}
