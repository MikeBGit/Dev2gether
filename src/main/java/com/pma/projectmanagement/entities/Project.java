package com.pma.projectmanagement.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectId;

//    mappedBy is the name found on Student
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST},
            fetch = FetchType.LAZY)
    @JoinTable(name="project_student",
                joinColumns = @JoinColumn(name="project_id"),
                inverseJoinColumns = @JoinColumn(name="student_id")
//            From Project, the foreign key is project_id
    )
    @JsonIgnore// ignored for serialization in api
    private List<Student> students;

    private String name;

    private String stage; //NOT STARTED, COMPLETED, NOT STARTED

    private String description;

    public Project(String name, String stage, String description) {
    }
    public void addStudent(Student student){
        if(students == null) {
            students = new ArrayList<>();
        }
        students.add(student);
    }

}
