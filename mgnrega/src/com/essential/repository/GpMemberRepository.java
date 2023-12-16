package com.essential.repository;

import java.util.List;
import java.util.Optional;

import com.essential.dto.GpMemberUpdatedDto;
import com.essential.entites.GpMember;

public interface GpMemberRepository {

	 public  Optional<GpMember>  addGramPanchatyatMember(GpMember ob);
	  public  Optional<GpMember>  updateGpMember(Integer gpid,GpMemberUpdatedDto ob);
	  public  Optional<GpMember>  removeGpMember(Integer gpid);
	  public  Optional<List<GpMember>>  getAllGpMember();
	  public  Optional<GpMember>  getGpMember(Integer gpid);
}
