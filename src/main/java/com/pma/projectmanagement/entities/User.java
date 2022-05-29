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
@NoArgsConstructor
@AllArgsConstructor

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role;
    private boolean enabled = true;

    //    RULES FOR DELETing on the child side. in the event something happens to the parent, these rules apply to the child
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST},
            fetch = FetchType.LAZY)
//    Lazy loading is better for performance

    @JoinTable(name="project_user",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="project_id")
//            From Project, the foreign key is project_id
    )
    @JsonIgnore // ignored for serialization in api
    private List<Project> projects;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user")
    private Comment comment;

    @ManyToMany
    private List<Comment> upvotedComments;

    public User(Long id, String username, String firstName, String lastName, String email, String password, String role, boolean enabled) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
