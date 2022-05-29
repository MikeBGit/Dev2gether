package com.pma.projectmanagement.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    @JoinTable(name="collaborated_project",
                joinColumns = @JoinColumn(name="project_id"),
                inverseJoinColumns = @JoinColumn(name="user_id")
//            From Project, the foreign key is project_id
    )
    @JsonIgnore// ignored for serialization in api
    private List<User> collaborators;

    @ManyToOne
    @JoinColumn(name="owner_id", nullable = false)
    private User projectOwner;

    private String name;

    private String stage; //NOT STARTED, COMPLETED, NOT STARTED

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

    public Project(String name, String stage, String description) {
    }

    public Project(Long id, String name, String stage, String description) {
        this.id = id;
        this.name = name;
        this.stage = stage;
        this.description = description;
    }

    public void addCollaborator(User user){
        if(collaborators == null) {
            collaborators = new ArrayList<>();
        }
        collaborators.add(user);
    }



}
