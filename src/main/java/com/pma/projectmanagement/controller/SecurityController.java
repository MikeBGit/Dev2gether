package com.pma.projectmanagement.controller;

import com.pma.projectmanagement.dao.StudentRepository;
import com.pma.projectmanagement.entities.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SecurityController {

    @Autowired
    BCryptPasswordEncoder bCryptEncoder;

    @Autowired
    StudentRepository studentRepository;

    @GetMapping("/register")
    public String register(Model model){

        Student student = new Student();
        model.addAttribute("studentReg", student);
        return "security/register";

    }

    @PostMapping("/register/save")
    public String registerStudent(Model model, Student student){

        student.setPassword(bCryptEncoder.encode(student.getPassword()));
        studentRepository.save(student);
        return "redirect:/";

    }


}
