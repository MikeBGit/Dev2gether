package com.pma.projectmanagement.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Data
@Entity
@IdClass(CommentUpvoteKey.class)
@NoArgsConstructor
public class CommentUpvote {
//
//  @EmbeddedId
//  private CommentUpvoteKey id;

//  @ManyToOne
////  @JoinColumn(name="comment_id", insertable = false, updatable = false)
//  @JoinColumn(name="comment_id", referencedColumnName="id")
  @Id
  private Long commentId;

//  @ManyToOne
////  @JoinColumn(name="user_id", insertable = false, updatable = false)
//  @JoinColumn(name="user_id", referencedColumnName="id")
  @Id
  private Long userId;

  public CommentUpvote(Long commentId, Long userId) {
  }
}
