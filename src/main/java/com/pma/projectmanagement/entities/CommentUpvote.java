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

  @Id
  private Long commentId;

  @Id
  private Long userId;

  public CommentUpvote(Long commentId, Long userId) {
  }
}
