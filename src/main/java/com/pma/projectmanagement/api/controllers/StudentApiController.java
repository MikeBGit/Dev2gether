package com.pma.projectmanagement.api.controllers;

import com.pma.projectmanagement.dao.StudentRepository;
import com.pma.projectmanagement.entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app-api/students")
public class StudentApiController {
    @Autowired
    StudentRepository studentRepository;

    @GetMapping
    public Iterable<Student> getStudents(){
        return studentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable("id")Long id){
        return studentRepository.findById(id).get();
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Student create(@RequestBody Student student){
        return studentRepository.save(student) ;
    }

    @PutMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Student update(@RequestBody Student student) {
        return studentRepository.save(student);
    }


//    Use Patch for updating cause PUT re-creates the record and destroys old relations in the DB, this preserves them
    @PatchMapping(path="/{id}", consumes = "application/json")
    public Student partialUpdate(@PathVariable("id")long id , @RequestBody Student patchStudent){
        Student student = studentRepository.findById(id).get();

        if(patchStudent.getEmail() != null){
//            If the requestbody has an email, set new email.
            student.setEmail(patchStudent.getEmail());
        }
        if(patchStudent.getFirstName() != null){
//            If the requestbody has a firstName, set new email.
            student.setFirstName(patchStudent.getFirstName());
        }
        if(patchStudent.getLastName() != null){
//            If the requestbody has a lastName, set new email.
            student.setLastName(patchStudent.getLastName());
        }

        return studentRepository.save(student);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {

        try{
        studentRepository.deleteById(id);

        } catch(EmptyResultDataAccessException exception){

        }
    }
}
