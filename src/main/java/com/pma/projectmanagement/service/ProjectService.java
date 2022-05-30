package com.pma.projectmanagement.service;

import com.pma.projectmanagement.dao.ProjectRepository;
import com.pma.projectmanagement.entities.Project;
import com.pma.projectmanagement.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

  @Autowired
  ProjectRepository projectRepository;

  public List<Project> getAllProjects() {
    List<Project> projects = new ArrayList<>();
    projects.addAll(projectRepository.findAll());
    return projects;
  }

  public Optional<Project> getProject(Long id) {
    return projectRepository.findById(id);
  }

  public void addProject(Project project) {
    projectRepository.save(project);
  }

  public void updateProject(Project project) {
    projectRepository.save(project);
  }

  public void deleteProject(long id) {
    projectRepository.deleteById(id);
  }

  public List<Project> getProjectsByProjectOwner(User user) {
    List<Project> projects = new ArrayList<>();
    projects.addAll(projectRepository.findByProjectOwner(user));
    return projects;
  }

}

