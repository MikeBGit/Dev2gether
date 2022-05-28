package com.pma.projectmanagement.dao;


import com.pma.projectmanagement.dto.StudentProject;
import com.pma.projectmanagement.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    @Override
    List<Student> findAll();
    @Query(nativeQuery=true, value = "select stu.first_name as firstName, stu.last_name as lastName, COUNT(pro.student_id) as projectCount " +
            "FROM student stu left join project_student pro ON pro.student_id = stu.student_id " +
            "group by stu.first_name, stu.last_name ORDER BY 3 desc")
    public List<StudentProject> studentProjects();

}
