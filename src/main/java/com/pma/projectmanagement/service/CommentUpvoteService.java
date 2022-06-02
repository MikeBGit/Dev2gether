package com.pma.projectmanagement.service;

import com.pma.projectmanagement.dao.CommentUpvoteRepository;
import com.pma.projectmanagement.entities.CommentUpvote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentUpvoteService {

  @Autowired
  CommentUpvoteRepository commentUpvoteRepository;

  public List<CommentUpvote> getCommentUpvotesByUserId(Long userId) {
    List<CommentUpvote> commentUpvotes = new ArrayList<>();
    commentUpvotes.addAll(commentUpvoteRepository.findByUserId(userId));
    return commentUpvotes;
  }

  public Optional<CommentUpvote> getCommentUpvote(CommentUpvote commentUpvote) {
    return Optional.ofNullable(commentUpvoteRepository.findByCommentIdAndUserId(commentUpvote.getCommentId(), commentUpvote.getUserId()));
  }

  public void addCommentUpvote(CommentUpvote commentUpvote) {
    commentUpvoteRepository.save(commentUpvote);
  }

  public void deleteCommentUpvote(CommentUpvote commentUpvote) {
    commentUpvoteRepository.delete(commentUpvote);
  }
}
