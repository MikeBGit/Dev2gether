package com.pma.projectmanagement.controller;

import com.pma.projectmanagement.dao.StudentRepository;
import com.pma.projectmanagement.entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @GetMapping
    public String displayStudents(Model model){

       List<Student> students = studentRepository.findAll();
       model.addAttribute("students", students);


       return "students/list-students";

    }

    @GetMapping("/new")
    public String studentForm(Model model){
        model.addAttribute("student", new Student());
        return "students/new-student";
    }

    @PostMapping("/save")
    public String newStudent(Student student, Model model){
        studentRepository.save(student);
        return "redirect:new";
    }
}
