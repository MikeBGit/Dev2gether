package com.pma.projectmanagement.controller;

import com.pma.projectmanagement.entities.Comment;
import com.pma.projectmanagement.entities.CommentUpvote;
import com.pma.projectmanagement.entities.User;
import com.pma.projectmanagement.service.CommentService;
import com.pma.projectmanagement.service.CommentUpvoteService;
import com.pma.projectmanagement.service.ProjectService;
import com.pma.projectmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.stream.Collectors;

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

//    List<Comment> comments = commentService.findByProjectIdOrderByCreatedTimestampDesc(projectId);
//    List<Long> upvotedComments = commentUpvoteService.getCommentUpvotesByUserId(userId).stream().map(upvote -> commentUpvote.getCommentId()).collect(Collectors.toList());
//    model.addAttribute("comments", comments);
////      model.addAttribute("commentUpvote", new CommentUpvote());
//    model.addAttribute("upvotedComments", upvotedComments);
////      model.addAttribute("comment", new Comment());
//    model.addAttribute("project", projectService.getProject(projectId).get());
//    model.addAttribute("user.html", userService.getUser(userId).get());
    return "redirect:/projects/{projectId}/";
  }
}
