package com.pma.projectmanagement.dao;


import com.pma.projectmanagement.dto.ProjectStatus;
import com.pma.projectmanagement.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
//    @Override
//    List<Project> findAll();
    @Query(nativeQuery=true, value = "SELECT  stage as label , count(stage) as value " +
            "from project " +
            "group by stage order by 2 desc")

//    HERES WHERE THE DTO COMES IN AS TYPE
    List<ProjectStatus> projectStatusCount();
}
