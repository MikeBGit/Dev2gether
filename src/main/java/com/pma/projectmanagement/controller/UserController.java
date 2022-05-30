package com.pma.projectmanagement.controller;
import com.pma.projectmanagement.entities.User;
import com.pma.projectmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping("/")
  public List<User> getAllUsers() {
    return userService.getAllUsers();
  }

  @GetMapping
  public String displayUsers(Model model){

    List<User> users = userService.getAllUsers();
    model.addAttribute("users", users);

    return "users/list-users";
  }

  @GetMapping("/new")
  public String userForm(Model model){
    model.addAttribute("user", new User());
    return "users/new-user";
  }

  @PostMapping("/save")
  public String newUser(User user, Model model){
    userService.addUser(user);
    return "redirect:new";
  }

  @GetMapping("/{id}")
  public String displayUser(@PathVariable Long id, Model model){
    model.addAttribute("user", userService.getUser(id).get());
    return "users/user";
  }

}
