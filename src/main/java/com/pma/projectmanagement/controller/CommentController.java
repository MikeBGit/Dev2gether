package com.pma.projectmanagement.controller;

import com.pma.projectmanagement.entities.Comment;
import com.pma.projectmanagement.entities.CommentUpvote;
import com.pma.projectmanagement.entities.Project;
import com.pma.projectmanagement.entities.User;
import com.pma.projectmanagement.service.CommentService;
import com.pma.projectmanagement.service.CommentUpvoteService;
import com.pma.projectmanagement.service.ProjectService;
import com.pma.projectmanagement.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CommentController {

  @Autowired
  CommentService commentService;

  @Autowired
  CommentUpvoteService commentUpvoteService;

  @Autowired
  ProjectService projectService;

  @Autowired
  UserService userService;

//  @GetMapping("/projects/{projectId}")
//  public String index(@PathVariable Long projectId, Model model) {
//    List<Comment> comments = commentService.commentRepository.findByProjectId(projectId);
//    model.addAttribute("comments", comments);
//
//    model.addAttribute("comment", new Comment());
//    model.addAttribute("project", projectService.getProject(projectId).get());
//    return "project-page";
//  }
//
//  @PostMapping("/projects/{projectId}")
//  public String store(@Valid @ModelAttribute("comment") Comment comment, @PathVariable Long projectId, BindingResult result) {
//    comment.setProject(new Project(projectId, "", ""));
//    commentService.addComment(comment);
//    return "redirect:/projects/{projectId}";
//  }

  @GetMapping("/projects/{projectId}/{userId}")
  public String getNewestComments(@PathVariable Long projectId, @PathVariable Long userId, Model model) {
    List<Comment> comments = commentService.getNewestComments(projectId);
    List<Long> upvotedComments = commentUpvoteService.getCommentUpvotesByUserId(userId).stream().map(commentUpvote -> commentUpvote.getCommentId()).collect(Collectors.toList());
    model.addAttribute("comments", comments);
    model.addAttribute("commentUpvote", new CommentUpvote());
    model.addAttribute("upvotedComments", upvotedComments);
    model.addAttribute("comment", new Comment());
    model.addAttribute("project", projectService.getProject(projectId).get());
    model.addAttribute("user", userService.getUser(userId).get());
    return "projects/project";
  }

  @GetMapping("/projects/{projectId}/{userId}/most-helpful")
  public String getMostHelpfulComments(@PathVariable Long projectId, @PathVariable Long userId, Model model) {
    List<Comment> comments = commentService.getMostHelpfulComments(projectId);
    List<Long> upvotedComments = commentUpvoteService.getCommentUpvotesByUserId(userId).stream().map(commentUpvote -> commentUpvote.getCommentId()).collect(Collectors.toList());
    model.addAttribute("comments", comments);
    model.addAttribute("commentUpvote", new CommentUpvote());
    model.addAttribute("upvotedComments", upvotedComments);
    model.addAttribute("comment", new Comment());
    model.addAttribute("project", projectService.getProject(projectId).get());
    model.addAttribute("user", userService.getUser(userId).get());
    return "projects/project";
  }
  @GetMapping("/projects/{projectId}/{userId}/oldest")
    public String getOldestComments(@PathVariable Long projectId, @PathVariable Long userId, Model model) {
    List<Comment> comments = commentService.getOldestComments(projectId);
    List<Long> upvotedComments = commentUpvoteService.getCommentUpvotesByUserId(userId).stream().map(commentUpvote -> commentUpvote.getCommentId()).collect(Collectors.toList());
    model.addAttribute("comments", comments);
    model.addAttribute("commentUpvote", new CommentUpvote());
    model.addAttribute("upvotedComments", upvotedComments);
    model.addAttribute("comment", new Comment());
    model.addAttribute("project", projectService.getProject(projectId).get());
    model.addAttribute("user", userService.getUser(userId).get());
    return "projects/project";
  }

  @PostMapping("/projects/{projectId}/{userId}")
  public String store(@Valid @ModelAttribute("comment") Comment comment, @PathVariable Long projectId, @PathVariable Long userId, BindingResult result) {
    comment.setProject(new Project(projectId, "", "", ""));

    comment.setUser(new User(userId, "", "", "", "", "", true));
    commentService.addComment(comment);
    return "redirect:/projects/{projectId}/{userId}";
  }

  @GetMapping("/comments/delete/{commentId}")
  public String delete(@PathVariable Long commentId) {
    commentService.deleteComment(commentId);
    return "redirect:/projects/10/10";
  }
}
