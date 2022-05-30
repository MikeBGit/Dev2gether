package com.pma.projectmanagement.service;

import com.pma.projectmanagement.dao.UserRepository;
import com.pma.projectmanagement.dto.UserProject;
import com.pma.projectmanagement.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

  @Autowired
  UserRepository userRepository;

  public List<User> getAllUsers() {
    List<User> users = new ArrayList<>();
    users.addAll(userRepository.findAll());
    return users;
  }

  public Optional<User> getUser(Long id) {
    return userRepository.findById(id);
  }

  public Optional<User> getUserByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  public void addUser(User user) {
    userRepository.save(user);
  }

  public void updateUser(User user) {
    userRepository.save(user);
  }

  public void deleteUser(long id) {
    userRepository.deleteById(id);
  }

  public List<UserProject> getUserProjects() {
    return userRepository.userProjects();
  }

}


