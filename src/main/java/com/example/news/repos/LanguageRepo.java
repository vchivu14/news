package com.example.news.repos;

import com.example.news.entities.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepo extends JpaRepository<Language,Integer> {
    Language findByName(String name);
}
