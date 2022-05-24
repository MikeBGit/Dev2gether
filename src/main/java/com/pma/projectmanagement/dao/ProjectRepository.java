package com.pma.projectmanagement.dao;


import com.pma.projectmanagement.entities.Project;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProjectRepository extends CrudRepository<Project, Long> {
    @Override
    List<Project> findAll();
}
