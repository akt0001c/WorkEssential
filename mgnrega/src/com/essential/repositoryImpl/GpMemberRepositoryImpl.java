package com.essential.repositoryImpl;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.essential.dto.GpMemberUpdatedDto;
import com.essential.entites.GpMember;
import com.essential.repository.GpMemberRepository;

public class GpMemberRepositoryImpl implements GpMemberRepository {

	@Override
	public Optional<GpMember> addGramPanchatyatMember(GpMember ob) throws SQLException {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<GpMember> updateGpMember(Integer gpid, GpMemberUpdatedDto ob) throws SQLException {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<GpMember> removeGpMember(Integer gpid) throws SQLException {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<List<GpMember>> getAllGpMember() throws SQLException {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<GpMember> getGpMember(Integer gpid) throws SQLException {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

}
