package com.pma.projectmanagement.controller;
import com.pma.projectmanagement.entities.CommentUpvote;
import com.pma.projectmanagement.entities.Project;

import com.pma.projectmanagement.entities.User;
import com.pma.projectmanagement.service.ProjectService;
import com.pma.projectmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    ProjectService projectService;
    @Autowired
    UserService userService;

    @GetMapping
    public String displayProjects(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByEmail(auth.getName()).get();
        model.addAttribute("user", user);

        List<Project> projects = projectService.getAllProjects();
        model.addAttribute("projects", projects);
        return "projects/list-projects";
    }

    @GetMapping("/new")
    public String displayProjectForm(Model model){


        Project project = new Project();
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("project", project);

        return "projects/new-project";
    }

    @PostMapping("/save")
    public String createProject(Project project, Model model){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        project.setProjectOwner(userService.getUserByEmail(auth.getName()).get());
//        This should handle the saving to the database
        projectService.addProject(project);



//      CODE FOR ONE TO MANY STUDENT PROJECT ASSIGN
//        Iterable<Student> chosenStudents = studentRepository.findAllById(students);
//
//        for(Student student: chosenStudents){
//            student.setProject(project);
//            studentRepository.save(student);
//        }

//        Use a Redirect to prevent duplicate submissions
        return "redirect:/projects";
    }

}
