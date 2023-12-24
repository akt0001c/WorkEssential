package com.essential.repositoryImpl;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.essential.dto.WagesUpdatedDto;
import com.essential.entites.Wages;
import com.essential.repository.WagesRepository;

public class WagesRepositoryImpl implements WagesRepository {

	@Override
	public Optional<Wages> addWages(Wages ob) throws SQLException {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<Wages> updateWagesDetails(Integer wid, WagesUpdatedDto ob) throws SQLException {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<Wages> removeWages(Integer wid) throws SQLException {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<List<Wages>> getAllWages() throws SQLException {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<Wages> getWages(Integer wid) throws SQLException {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

}
