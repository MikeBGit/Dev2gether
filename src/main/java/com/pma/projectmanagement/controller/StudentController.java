//package com.pma.projectmanagement.controller;
//
//import com.pma.projectmanagement.dao.StudentRepository;
//import com.pma.projectmanagement.dao.UserRepository;
//import com.pma.projectmanagement.entities.Student;
//import com.pma.projectmanagement.entities.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import java.util.List;
//
//@Controller
//@RequestMapping("/users")
//public class StudentController {
//
//    @Autowired
//    UserRepository userRepository;
//
//    @GetMapping
//    public String displayUsers(Model model){
//
//       List<User> users = userRepository.findAll();
//       model.addAttribute("users", users);
//
//
//       return "students/list-students";
//
//    }
//
//    @GetMapping("/new")
//    public String studentForm(Model model){
//        model.addAttribute("student", new Student());
//        return "students/new-student";
//    }
//
//    @PostMapping("/save")
//    public String newStudent(User student, Model model){
//        studentRepository.save(student);
//        return "redirect:new";
//    }
//}
