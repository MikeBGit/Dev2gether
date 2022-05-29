package com.pma.projectmanagement.api.controllers;

import com.pma.projectmanagement.dao.ProjectRepository;
import com.pma.projectmanagement.entities.Project;
import com.pma.projectmanagement.entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app-api/projects")
public class ProjectApiController {

    @Autowired
    ProjectRepository projectRepository;


    @GetMapping
    public Iterable<Project> getProjects(){
        return projectRepository.findAll();
    }

    @GetMapping("/{id}")
    public Project getProjectById(@PathVariable("id")Long id){
        return projectRepository.findById(id).get();
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Project create(@RequestBody Project project){
        return projectRepository.save(project) ;
    }

    //    Use Patch for updating cause PUT re-creates the record and destroys old relations in the DB, this preserves them
    @PatchMapping(path="/{id}", consumes = "application/json")
    public Project partialUpdate(@PathVariable("id")long id , @RequestBody Project patchProject){
        Project project = projectRepository.findById(id).get();

        if(patchProject.getStudents() != null){
//            If the requestbody has an email, set new email.
            project.setStudents(patchProject.getStudents());
        }
        if(patchProject.getName() != null){
//            If the requestbody has a firstName, set new email.
            project.setName(patchProject.getName());
        }
        if(patchProject.getStage() != null){
//            If the requestbody has a lastName, set new email.
            project.setStage(patchProject.getStage());
        }
        if(patchProject.getDescription() != null){
//            If the requestbody has a lastName, set new email.
            project.setDescription(patchProject.getDescription());
        }

        return projectRepository.save(project);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {

        try{
            projectRepository.deleteById(id);

        } catch(EmptyResultDataAccessException exception){

        }
    }

}
