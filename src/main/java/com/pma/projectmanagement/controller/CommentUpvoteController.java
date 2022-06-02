package com.pma.projectmanagement.controller;

import com.pma.projectmanagement.entities.CommentUpvote;
import com.pma.projectmanagement.service.CommentService;
import com.pma.projectmanagement.service.CommentUpvoteService;
import com.pma.projectmanagement.service.ProjectService;
import com.pma.projectmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CommentUpvoteController {

  @Autowired
  CommentService commentService;

  @Autowired
  ProjectService projectService;

  @Autowired
  UserService userService;

  @Autowired
  CommentUpvoteService commentUpvoteService;

  @PostMapping("/projects/{projectId}/commentUpvote/store")
  public String store(@ModelAttribute("commentUpvote") CommentUpvote commentUpvote) {

    if (commentUpvoteService.getCommentUpvote(commentUpvote).isPresent())
      commentUpvoteService.deleteCommentUpvote(commentUpvote);
    else
      commentUpvoteService.addCommentUpvote(commentUpvote);

    return "redirect:/projects/{projectId}/";
  }
}
