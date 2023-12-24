package com.essential.repositoryImpl;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.essential.dto.ProjectUpdatedDto;
import com.essential.entites.Project;
import com.essential.repository.ProjectRepository;

public class ProjectRepositoryImpl implements ProjectRepository {

	@Override
	public Optional<Project> addProject(Project ob)  throws SQLException{
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<Project> updateProjectDetails(Integer pid, ProjectUpdatedDto ob)  throws SQLException {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<Project> removeProject(Integer pid)  throws SQLException{
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<List<Project>> getAllProjects()  throws SQLException {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<Project> getBdo(Integer pid)  throws SQLException {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

}
