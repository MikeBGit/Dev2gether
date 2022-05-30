package com.pma.projectmanagement.service;

import com.pma.projectmanagement.dao.CommentRepository;
import com.pma.projectmanagement.entities.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

  @Autowired
  CommentRepository commentRepository;

  public Optional<Comment> getComment(Long id) {
    return commentRepository.findById(id);
  }


  public List<Comment> getAllComments() {
    List<Comment> comments = new ArrayList<>();
    comments.addAll(commentRepository.findAll());
    return comments;
  }

  public  List<Comment> getNewestComments(Long projectId) {
    List<Comment> comments = new ArrayList<>();
    comments.addAll(commentRepository.findByProjectIdOrderByCreatedTimestampDesc(projectId));
    return comments;
  }

  public  List<Comment> getOldestComments(Long projectId) {
    List<Comment> comments = new ArrayList<>();
    comments.addAll(commentRepository.findByProjectIdOrderByCreatedTimestampAsc(projectId));
    return comments;
  }

  public  List<Comment> getMostHelpfulComments(Long projectId) {
    List<Comment> comments = new ArrayList<>();
    comments.addAll(commentRepository.findByProjectIdOrderByNumOfUpvotesDesc(projectId));
    return comments;
  }


  public void addComment(Comment comment) {
    commentRepository.save(comment);
  }

  public void deleteComment(Long commentId) {
    commentRepository.deleteById(commentId);
  }


}
