package com.pma.projectmanagement.controller;

import com.pma.projectmanagement.entities.User;
import com.pma.projectmanagement.exception.RecordNotFoundException;
import com.pma.projectmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
  @GetMapping("/view")
  public String viewProfile(Model model){
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    User user = userService.getUserByEmail(auth.getName()).orElse(null);
    model.addAttribute("user", user);
    return "users/user";
  }

  @GetMapping("/update")
  public String updateForm( Model model) throws RecordNotFoundException {

    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    User user = userService.getUserByEmail(auth.getName()).orElse(null);

    model.addAttribute("userDetails", user);
    return "users/update-profile";
  }


  @PostMapping(path="/update-save")
  public String partialUpdate(User patchUser){

    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    User user = userService.getUserByEmail(auth.getName()).orElse(null);
//

    if(patchUser.getFirstName() != null){
//            If the requestbody has a firstName, set new email.
      user.setFirstName(patchUser.getFirstName());
    }
//
    if(patchUser.getLastName() != null){
//            If the requestbody has a lastName, set new email.
      user.setLastName(patchUser.getLastName());
    }

    userService.updateUser(user);

    return "redirect:/dashboard";

  }

}