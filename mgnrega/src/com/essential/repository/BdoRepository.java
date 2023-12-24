package com.essential.repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.essential.dto.BdoUpdatedDto;
import com.essential.entites.Bdo;
import com.essential.exceptions.BdoNotFoundException;
import com.essential.exceptions.OperationFaliureException;

public interface BdoRepository {
  public  Optional<Bdo>  addBdo(Bdo ob) throws SQLException, OperationFaliureException;
  public  Optional<Bdo>  updateBdoDetails(Integer bid,BdoUpdatedDto ob) throws SQLException,BdoNotFoundException, OperationFaliureException;
  public  Optional<Bdo>  removeBdo(Integer bid) throws SQLException, BdoNotFoundException, OperationFaliureException;
  public  Optional<List<Bdo>>  getAllBdo() throws SQLException, OperationFaliureException;
  public  Optional<Bdo>  getBdo(Integer bid) throws SQLException, OperationFaliureException;
}
