package com.pma.projectmanagement.dao;


import com.pma.projectmanagement.entities.Language;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LanguageRepository extends CrudRepository<Language, Long> {
    @Override
    List<Language> findAll();
}