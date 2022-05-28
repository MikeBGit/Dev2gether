package com.pma.projectmanagement.controller;

import com.pma.projectmanagement.entities.CommentUpvote;
import com.pma.projectmanagement.service.CommentUpvoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CommentUpvoteController {

  @Autowired
  CommentUpvoteService commentUpvoteService;

  @PostMapping("commentUpvote/store")
  public String store(@ModelAttribute("commentUpvote") CommentUpvote commentUpvote) {

    if (commentUpvoteService.getCommentUpvote(commentUpvote).isPresent()) {
      commentUpvoteService.deleteCommentUpvote(commentUpvote);
      return "redirect:/projects/1/1";
    }

    commentUpvoteService.addCommentUpvote(commentUpvote);
    return "redirect:/projects/1/1";
  }
}
