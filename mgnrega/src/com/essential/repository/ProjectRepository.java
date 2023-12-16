package com.essential.repository;

import java.util.List;
import java.util.Optional;

import com.essential.dto.ProjectUpdatedDto;
import com.essential.entites.Project;

public interface ProjectRepository {
  public  Optional<Project>  addProject(Project ob);
  public  Optional<Project>  updateProjectDetails(Integer pid,ProjectUpdatedDto ob);
  public  Optional<Project>  removeProject(Integer pid);
  public  Optional<List<Project>>  getAllProjects();
  public  Optional<Project>  getBdo(Integer pid);
  
}
