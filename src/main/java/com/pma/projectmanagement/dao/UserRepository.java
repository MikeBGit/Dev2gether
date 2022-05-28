package com.pma.projectmanagement.dao;

import com.pma.projectmanagement.dto.UserProject;
import com.pma.projectmanagement.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(nativeQuery=true, value = "select usr.first_name as firstName, usr.last_name as lastName, COUNT(pro.user_id) as projectCount " +
          "FROM user usr left join project_user pro ON pro.student_id = usr.user_id " +
          "group by usr.first_name, usr.last_name ORDER BY 3 desc")
    public List<UserProject> userProjects();
}
