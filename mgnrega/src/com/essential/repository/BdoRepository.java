package com.essential.repository;

import java.util.List;
import java.util.Optional;

import com.essential.dto.BdoUpdatedDto;
import com.essential.entites.Bdo;

public interface BdoRepository {
  public  Optional<Bdo>  addBdo(Bdo ob);
  public  Optional<Bdo>  updateBdoDetails(Integer bid,BdoUpdatedDto ob);
  public  Optional<Bdo>  removeBdo(Integer bid);
  public  Optional<List<Bdo>>  getAllBdo();
  public  Optional<Bdo>  getBdo(Integer bid);
}
