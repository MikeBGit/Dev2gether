//package com.pma.projectmanagement.api.controllers;
//
//import com.pma.projectmanagement.dao.StudentRepository;
//import com.pma.projectmanagement.entities.Student;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/app-api/students")
//public class StudentApiController {
//    @Autowired
//    StudentRepository studentRepository;
//
//    @GetMapping
//    public Iterable<Student> getStudents(){
//        return studentRepository.findAll();
//    }
//
//    @GetMapping("/{id}")
//    public Student getStudentById(@PathVariable("id")Long id){
//        return studentRepository.findById(id).get();
//    }
//
//    @PostMapping(consumes = "application/json")
//    @ResponseStatus(HttpStatus.CREATED)
//    public Student create(@RequestBody Student student){
//        return studentRepository.save(student) ;
//    }
//}
