package com.essential.repository;

import java.util.List;
import java.util.Optional;

import com.essential.dto.WagesUpdatedDto;
import com.essential.entites.Wages;

public interface WagesRepository {
	public  Optional<Wages>  addWages(Wages ob);
	  public  Optional<Wages>  updateProjectDetails(Integer wid,WagesUpdatedDto ob);
	  public  Optional<Wages>  removeWages(Integer wid);
	  public  Optional<List<Wages>>  getAllWages();
	  public  Optional<Wages>  getWages(Integer wid);
	
}
