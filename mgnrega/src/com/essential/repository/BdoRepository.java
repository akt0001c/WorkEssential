package com.essential.repository;

import java.util.Optional;

import com.essential.dto.BdoUpdateDto;
import com.essential.entites.Bdo;

public interface BdoRepository {
   Optional<Bdo>  addBdo(Bdo ob);
   Optional<Bdo>  update(Integer bid,BdoUpdateDto ob);
   Optional<Bdo>  removeBdo(Integer bid);
   Optional<Bdo>  getAllBdo();
   Optional<Bdo>  getBdo(Integer bid);
}
