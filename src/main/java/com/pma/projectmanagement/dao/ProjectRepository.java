package com.pma.projectmanagement.dao;


import com.pma.projectmanagement.dto.ProjectStatus;
import com.pma.projectmanagement.entities.Project;
import com.pma.projectmanagement.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
//    @Override
//    List<Project> findAll();
    @Query(nativeQuery=true, value = "SELECT  state as label , count(state) as value " +
            "from project " +
            "group by state order by 2 desc")

//    HERES WHERE THE DTO COMES IN AS TYPE
    List<ProjectStatus> projectStatusCount();

    List<Project> findByProjectOwner(User user);


    @Query(nativeQuery=true, value ="SELECT * FROM project p WHERE p.state LIKE :status and p.owner_id = :owner")
    List<Project> findProjectsByStatus(@Param("status") String state,
                                         @Param("owner") Long owner);
}