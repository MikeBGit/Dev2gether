package com.pma.projectmanagement.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.pma.projectmanagement.dao.ProjectRepository;
import com.pma.projectmanagement.dto.ProjectStatus;
import com.pma.projectmanagement.dto.UserProject;

import com.pma.projectmanagement.entities.Project;
import com.pma.projectmanagement.entities.User;
import com.pma.projectmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

//    @Value("${version}")
//    private String ver;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String displayHome(Model model) throws JsonProcessingException {
//        model.addAttribute("versionNumber", ver);

       Map<String,Object> map = new HashMap<>();

       List<Project> projects = projectRepository.findAll();
       model.addAttribute("projects", projects);

       List<User> users = userService.getAllUsers();
       model.addAttribute("users", users);

       List<UserProject> userProjects = userService.getUserProjects();
       model.addAttribute("userProjectCount", userProjects);

        List<ProjectStatus> projectStatusCount = projectRepository.projectStatusCount();
        model.addAttribute("projectStatusCount", projectStatusCount);

//        Lets Convert the projectStatusCount records into JSON for use in Js
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString =  objectMapper.writeValueAsString(projectStatusCount);
        model.addAttribute("projectStatusCount_CHART_DATA" , jsonString);
       return "main/home";
    }
  @GetMapping("/services")
  public String displayServices(Model model){

    return "main/services";

  }
  @GetMapping("/about")
  public String displayAbout(Model model){

    return "main/about";

  }
}
