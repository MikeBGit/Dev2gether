package com.pma.projectmanagement.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long languageId;

    //    mappedBy is the name found on Student
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST},
            fetch = FetchType.LAZY)
    @JoinTable(name="project_student",
            joinColumns = {@JoinColumn(name = "project_id"), @JoinColumn(name = "student_id")},
            inverseJoinColumns = @JoinColumn(name="language_id")
//            From Project, the foreign key is project_id
    )

    private List<Language> languages;

    private String name;


    public void addLanguage(Language language){
        if(language == null) {
            languages = new ArrayList<>();
        }
        languages.add(language);
    }

}