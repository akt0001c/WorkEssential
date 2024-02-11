package com.essential.repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.essential.dto.GpMemberUpdatedDto;
import com.essential.entites.GpMember;
import com.essential.exceptions.GpMemberNotFoundException;
import com.essential.exceptions.OperationFaliureException;

public interface GpMemberRepository {

	 public  Optional<GpMember>  addGramPanchatyatMember(GpMember ob)  throws SQLException, OperationFaliureException;
	  public  Optional<GpMember>  updateGpMember(Integer gpid,GpMemberUpdatedDto ob)  throws SQLException, OperationFaliureException;
	  public  Optional<GpMember>  removeGpMember(Integer gpid)  throws SQLException, OperationFaliureException, GpMemberNotFoundException;
	  public  Optional<List<GpMember>>  getAllGpMember()  throws SQLException, OperationFaliureException;
	  public  Optional<GpMember>  getGpMember(Integer gpid)  throws SQLException, OperationFaliureException;
}
