package com.pma.projectmanagement.controller;


import com.pma.projectmanagement.dao.StudentRepository;
import com.pma.projectmanagement.dao.ProjectRepository;
import com.pma.projectmanagement.entities.Student;
import com.pma.projectmanagement.entities.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    StudentRepository studentRepository;

    @GetMapping("/")
    public String displayHome(Model model) {

       List<Project> projects = projectRepository.findAll();
       model.addAttribute("projects", projects);

       List<Student> students = studentRepository.findAll();
       model.addAttribute("students", students);

       return "main/home";
    }
}
