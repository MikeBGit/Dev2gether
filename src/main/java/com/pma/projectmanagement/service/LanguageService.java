package com.pma.projectmanagement.service;

import com.pma.projectmanagement.dao.LanguageRepository;
import com.pma.projectmanagement.entities.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LanguageService {

    @Autowired
    LanguageRepository languageRepository;

    public List<Language> getAllLanguages() {
        List<Language> languages = new ArrayList<>();
        languages.addAll(languageRepository.findAll());
        return languages;
    }
    public void addLanguage(Language language) {languageRepository.save(language);}

}