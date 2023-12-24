package com.essential.repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.essential.dto.GpMemberUpdatedDto;
import com.essential.entites.GpMember;

public interface GpMemberRepository {

	 public  Optional<GpMember>  addGramPanchatyatMember(GpMember ob)  throws SQLException;
	  public  Optional<GpMember>  updateGpMember(Integer gpid,GpMemberUpdatedDto ob)  throws SQLException;
	  public  Optional<GpMember>  removeGpMember(Integer gpid)  throws SQLException;
	  public  Optional<List<GpMember>>  getAllGpMember()  throws SQLException;
	  public  Optional<GpMember>  getGpMember(Integer gpid)  throws SQLException;
}
