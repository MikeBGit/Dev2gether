package com.pma.projectmanagement.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "First name cannot be empty.")
    private String firstName;

    @NotEmpty(message = "Last name cannot be empty.")
    private String lastName;

    @Column(unique = true, name ="email")
    @Pattern(regexp = "^[a-zA-Z\\d+_.-]+@johnabbottcollege.net$", message = "Please sign up using your John Abbott College email address (example@johnabbottcollege.net).")
    private String email;

    @Size(min=8, message = "Password must be at least 8 characters long.")
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
    private List<Project> contributedProjects;

    @OneToMany(mappedBy="projectOwner")
    private List<Project> postedProjects;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Comment> comments;

    @ManyToMany
    private List<Comment> upvotedComments;

    public User(Long id, String firstName, String lastName, String email, String password, String role, boolean enabled) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }
}
