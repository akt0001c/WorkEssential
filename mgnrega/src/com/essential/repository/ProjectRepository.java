package com.essential.repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.essential.dto.ProjectUpdatedDto;
import com.essential.entites.Project;

public interface ProjectRepository {
  public  Optional<Project>  addProject(Project ob)  throws SQLException;
  public  Optional<Project>  updateProjectDetails(Integer pid,ProjectUpdatedDto ob)  throws SQLException;
  public  Optional<Project>  removeProject(Integer pid )  throws SQLException;
  public  Optional<List<Project>>  getAllProjects()  throws SQLException;
  public  Optional<Project>  getBdo(Integer pid)  throws SQLException;
  
}
