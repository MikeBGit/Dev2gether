package com.pma.projectmanagement.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
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
    private Long id;

//    mappedBy is the name found on Student
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST},
            fetch = FetchType.LAZY)
    @JoinTable(name="project_user",
                joinColumns = @JoinColumn(name="project_id"),
                inverseJoinColumns = @JoinColumn(name="user_id")
//            From Project, the foreign key is project_id
    )
    @JsonIgnore// ignored for serialization in api
    private List<User> contributors;

    @ManyToOne
    @JoinColumn(name="owner_id", nullable = false)
    private User projectOwner;

    private String name;

    private String state; //NOT STARTED, COMPLETED, NOT STARTED

    @Lob
    private String description;

    @CreationTimestamp
    @Column(name = "created_timestamp", columnDefinition = "TIMESTAMP")
    private LocalDateTime createdTimestamp;

    @UpdateTimestamp
    @Column(name = "modified_timestamp", columnDefinition = "TIMESTAMP")
    private LocalDateTime modifiedTimestamp;

    @OneToMany(mappedBy = "project")
    List<Comment> comments;

    public Project(String name, String state, String description) {
    }

    public Project(Long id, String name, String state, String description) {
        this.id = id;
        this.name = name;
        this.state = state;
        this.description = description;
    }

    public void addCollaborator(User user) {
        if(contributors == null) {
            contributors = new ArrayList<>();
        }
        contributors.add(user);
    }

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST},
            fetch = FetchType.LAZY)
    @JoinTable(name="project_language",
            joinColumns = @JoinColumn(name="project_id"),
            inverseJoinColumns = @JoinColumn(name="language_id")
//            From Project, the foreign key is project_id
    )
    @JsonIgnore
    private List<Project> languages;





}
