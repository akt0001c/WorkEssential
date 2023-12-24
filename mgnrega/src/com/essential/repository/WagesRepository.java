package com.essential.repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.essential.dto.WagesUpdatedDto;
import com.essential.entites.Wages;

public interface WagesRepository {
	public  Optional<Wages>  addWages(Wages ob)  throws SQLException;
	  public  Optional<Wages>  updateWagesDetails(Integer wid,WagesUpdatedDto ob)  throws SQLException;
	  public  Optional<Wages>  removeWages(Integer wid)  throws SQLException;
	  public  Optional<List<Wages>>  getAllWages()  throws SQLException;
	  public  Optional<Wages>  getWages(Integer wid)  throws SQLException;
	
}
