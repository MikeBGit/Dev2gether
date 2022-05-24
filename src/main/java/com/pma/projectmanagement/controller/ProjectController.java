package com.pma.projectmanagement.controller;


import com.pma.projectmanagement.dao.ProjectRepository;
import com.pma.projectmanagement.dao.StudentRepository;
import com.pma.projectmanagement.entities.Project;
import com.pma.projectmanagement.entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    StudentRepository studentRepository;

    @GetMapping
    public String displayProjects(Model model){
        List<Project> projects = projectRepository.findAll();
        model.addAttribute("projects", projects);
        return "projects/list-projects";
    }

    @GetMapping("/new")
    public String displayProjectForm(Model model){

        Project aProject = new Project();
        List<Student> students = studentRepository.findAll();

        model.addAttribute("allStudents", students);
        model.addAttribute("project", aProject);

        return "projects/new-project";
    }

    @PostMapping("/save")
    public String createProject(Project project, Model model){
//        This should handle the saving to the database
        projectRepository.save(project);



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
