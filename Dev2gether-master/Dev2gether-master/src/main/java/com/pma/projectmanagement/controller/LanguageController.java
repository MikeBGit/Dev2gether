package com.pma.projectmanagement.controller;

import com.pma.projectmanagement.dao.LanguageRepository;
import com.pma.projectmanagement.dao.StudentRepository;
import com.pma.projectmanagement.entities.Language;
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
public class LanguageController {

    @Autowired
    LanguageRepository languageRepository;

    @GetMapping
    public String displayStudents(Model model){
        List<Language> languages = languageRepository.findAll();
        model.addAttribute("languages", languages);
        return "languages/list-languages";

    }

    @GetMapping("/new")
    public String studentForm(Model model){
        model.addAttribute("language", new Language());
        return "languages/new-languages";
    }

    @PostMapping("/save")
    public String newStudent(Language language, Model model){
        languageRepository.save(language);
        return "redirect:new";
    }
}