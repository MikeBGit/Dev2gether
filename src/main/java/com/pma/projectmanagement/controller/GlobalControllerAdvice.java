//package com.pma.projectmanagement.controller;
//
//import com.pma.projectmanagement.entities.User;
//import com.pma.projectmanagement.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ModelAttribute;
//
//@ControllerAdvice
//public class GlobalControllerAdvice {
//
//  @Autowired
//  UserService userService;
//
//  @ModelAttribute("user.html")
//  public User populateUser() {
//    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//    User user.html = userService.getUserByEmail(auth.getName()).get();
//    return user.html;
//  }
//}
