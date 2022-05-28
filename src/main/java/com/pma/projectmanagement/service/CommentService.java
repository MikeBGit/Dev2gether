package com.pma.projectmanagement.service;

import com.pma.projectmanagement.dao.CommentRepository;
import com.pma.projectmanagement.entities.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

  @Autowired
  CommentRepository commentRepository;

  public List<Comment> getAllComments() {
    List<Comment> comments = new ArrayList<>();
    comments.addAll(commentRepository.findAll());
    return comments;
  }

  public  List<Comment> findByProjectIdOrderByCreatedTimestampDesc(Long projectId) {
    List<Comment> comments = new ArrayList<>();
    comments.addAll(commentRepository.findByProjectIdOrderByCreatedTimestampDesc(projectId));
    return comments;
  }


  public void addComment(Comment comment) {
    commentRepository.save(comment);
  }

  public void deleteComment(long id) {
    commentRepository.deleteById(id);
  }
}
