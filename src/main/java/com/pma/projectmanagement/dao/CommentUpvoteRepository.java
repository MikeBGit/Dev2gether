package com.pma.projectmanagement.dao;

import com.pma.projectmanagement.entities.CommentUpvote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentUpvoteRepository extends JpaRepository<CommentUpvote, Long> {

  CommentUpvote findByCommentIdAndUserId(Long commentId, Long userId);

  List<CommentUpvote> findByUserId(Long userId);
}
