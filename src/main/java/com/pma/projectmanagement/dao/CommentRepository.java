package com.pma.projectmanagement.dao;

import com.pma.projectmanagement.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

  List<Comment> findByProjectId(Long projectId);
  List<Comment> findByProjectIdOrderByCreatedTimestampDesc(Long projectId);
  List<Comment> findByProjectIdOrderByCreatedTimestampAsc(Long projectId);
  List<Comment> findByProjectIdOrderByNumOfUpvotesDesc(Long projectId);

}
