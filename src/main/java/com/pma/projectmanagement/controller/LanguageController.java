package com.pma.projectmanagement.controller;

import com.pma.projectmanagement.dao.LanguageRepository;
import com.pma.projectmanagement.entities.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/languages")
public class LanguageController {

    @Autowired
    LanguageRepository languageRepository;

    @GetMapping
    public String displayLanguages(Model model){
        List<Language> languages = languageRepository.findAll();
        model.addAttribute("languages", languages);
        return "languages/list-languages";

    }

    @GetMapping("/new")
    public String languageForm(Model model){
        model.addAttribute("language", new Language());
        return "languages/new-language";
    }

    @PostMapping("/save")
    public String newLanguage(Language language, Model model){
        languageRepository.save(language);
        return "redirect:new";
    }
}