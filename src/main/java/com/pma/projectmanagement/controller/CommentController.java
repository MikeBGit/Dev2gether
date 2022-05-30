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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.thymeleaf.model.IModel;

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

  @GetMapping("/projects/{projectId}")
  public String getNewestComments(@PathVariable Long projectId, Model model) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    User user = userService.getUserByEmail(auth.getName()).orElse(null);;
    model.addAttribute("user", user);

    List<Comment> comments = commentService.getNewestComments(projectId);
    if (user != null) {
      List<Long> upvotedComments = commentUpvoteService.getCommentUpvotesByUserId(user.getId()).stream().map(commentUpvote -> commentUpvote.getCommentId()).collect(Collectors.toList());
      model.addAttribute("upvotedComments", upvotedComments);
    }
    model.addAttribute("comments", comments);
    model.addAttribute("commentUpvote", new CommentUpvote());
    model.addAttribute("comment", new Comment());
    model.addAttribute("project", projectService.getProject(projectId).get());

    return "projects/project";
  }

  @GetMapping("/projects/{projectId}/comments/most-liked")
  public String getMostHelpfulComments(@PathVariable Long projectId, Model model) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    User user = userService.getUserByEmail(auth.getName()).orElse(null);
    model.addAttribute("user", user);

    List<Comment> comments = commentService.getMostLikedComments(projectId);
    if (user != null) {
      List<Long> upvotedComments = commentUpvoteService.getCommentUpvotesByUserId(user.getId()).stream().map(commentUpvote -> commentUpvote.getCommentId()).collect(Collectors.toList());
      model.addAttribute("upvotedComments", upvotedComments);
    }
    model.addAttribute("comments", comments);
    model.addAttribute("commentUpvote", new CommentUpvote());
    model.addAttribute("comment", new Comment());
    model.addAttribute("project", projectService.getProject(projectId).get());

    return "projects/project";
  }
  @GetMapping("/projects/{projectId}/comments/oldest")
    public String getOldestComments(@PathVariable Long projectId, Model model) {

    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    User user = userService.getUserByEmail(auth.getName()).orElse(null);
    model.addAttribute("user", user);

    List<Comment> comments = commentService.getOldestComments(projectId);
    if (user != null) {
      List<Long> upvotedComments = commentUpvoteService.getCommentUpvotesByUserId(user.getId()).stream().map(commentUpvote -> commentUpvote.getCommentId()).collect(Collectors.toList());
      model.addAttribute("upvotedComments", upvotedComments);
    }
    model.addAttribute("comments", comments);
    model.addAttribute("commentUpvote", new CommentUpvote());
    model.addAttribute("comment", new Comment());
    model.addAttribute("project", projectService.getProject(projectId).get());
    return "projects/project";
  }

  @PostMapping("/projects/{projectId}")
  public String store(@Valid @ModelAttribute("comment") Comment comment, @PathVariable Long projectId, BindingResult result) {
    comment.setProject(new Project(projectId, "", "", ""));

    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    User user = userService.getUserByEmail(auth.getName()).orElse(null);

    comment.setUser(new User(user.getId(), "", "", "", "", "", true));
    commentService.addComment(comment);
    return "redirect:/projects/{projectId}";
  }

  @GetMapping("/comments/delete/{commentId}/{projectId}")
  public String delete(@PathVariable Long commentId, @PathVariable Long projectId) {

//    Long projectId = commentService.getComment(commentId).get().getProject().getId();
//    model.addAttribute("projectId", projectId);

    commentService.deleteComment(commentId);

    return "redirect:/projects/{projectId}";
  }
}
