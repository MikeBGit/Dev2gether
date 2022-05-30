package com.pma.projectmanagement.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class CommentUpvoteKey implements Serializable {

  @Column(name="comment_id")
  private Long commentId;

  @Column(name="user_id")
  private Long userId;

}
