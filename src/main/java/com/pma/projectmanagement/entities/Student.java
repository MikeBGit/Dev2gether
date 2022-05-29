package com.pma.projectmanagement.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;

//    Security
    @Column(name = "username")
    private String userName;
    private String email;
    private String password;
    private String role;
    private boolean enabled = true;
//    Security

//    Student Info:
    private String firstName;
    private String lastName;


    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST}, //    RULES FOR DELETing on the child side. in the event something happens to the parent, these rules apply to the child
                fetch = FetchType.LAZY // Lazy loading is better for performance
                )

    @JoinTable(
            name="project_student",
            joinColumns = @JoinColumn(name="student_id"),
            inverseJoinColumns = @JoinColumn(name="project_id") // From Project, the foreign key is project_id
            )

    @JsonIgnore // ignored for serialization in api
    private List<Project> projects;

//    public Student(String firstname, String lastname, String email) {
//    }
}
