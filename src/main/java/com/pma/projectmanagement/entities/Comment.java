package com.pma.projectmanagement.entities;

//import dev2gether.project.Project;
//import dev2gether.user.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  private Project project;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;

  @Lob
  @NotEmpty
  private String content;

  @CreationTimestamp
  @Column(name = "created_timestamp", columnDefinition = "TIMESTAMP")
  private LocalDateTime createdTimestamp;

  @UpdateTimestamp
  @Column(name = "modified_timestamp", columnDefinition = "TIMESTAMP")
  private LocalDateTime modifiedTimestamp;

  @Formula("(select count(cu.comment_id) from comment_upvote cu where cu.comment_id = id)")
  @Column(name="num_of_upvotes")
  private Integer numOfUpvotes;
//  @ManyToMany
//  private List<User> upvotes = new ArrayList<>();

//  public Comment(String content) {
//    this.content = content;
//  }

}
