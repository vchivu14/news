package com.example.news.repos;

import com.example.news.entities.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import java.sql.Timestamp;
import java.util.List;

public interface NewsRepository extends JpaRepository<News, Integer> {
    List<News> findAllByTitleContaining(String title);
    List<News> findAllByAuthorContaining(String author);
    List<News> findAllByPublished(Timestamp timestamp);
    @RestResource(exported = false)
    Page<News> findAll(Pageable pageable);
    @RestResource(exported = false)
    List<News> findAllByPublished(Timestamp timestamp, Pageable pageable);
    @RestResource(exported = false)
    Page<News> findByTitleContaining(String title, Pageable pageable);

    @RestResource(exported = false)
    Page<News> findAllByAuthor(String author, Pageable pageable);
    @RestResource(exported = false)
    Page<News> findAllByCategory(String category, Pageable pageable);
    @RestResource(exported = false)
    Page<News> findAllByLanguage(String language, Pageable pageable);
    @RestResource(exported = false)
    Page<News> findAllBySource(String source, Pageable pageable);

    @RestResource(exported = false)
    Page<News> findAllByAuthorAndSource(String author, String source, Pageable pageable);
    @RestResource(exported = false)
    Page<News> findAllByAuthorAndCategory(String author, String category, Pageable pageable);
    @RestResource(exported = false)
    Page<News> findAllByAuthorAndLanguage(String author, String language, Pageable pageable);
    @RestResource(exported = false)
    Page<News> findAllByAuthorAndSourceAndLanguage(String author, String source, String language, Pageable pageable);
    @RestResource(exported = false)
    Page<News> findAllByAuthorAndSourceAndCategory(String author, String source, String category, Pageable pageable);
    @RestResource(exported = false)
    Page<News> findAllByAuthorAndLanguageAndCategory(String author, String language, String category, Pageable pageable);
    @RestResource(exported = false)
    Page<News> findAllByAuthorAndSourceAndLanguageAndCategory(String author, String source, String language, String category, Pageable pageable);

    @RestResource(exported = false)
    Page<News> findAllBySourceAndCategory(String source, String category, Pageable pageable);
    @RestResource(exported = false)
    Page<News> findAllBySourceAndLanguage(String source, String language, Pageable pageable);
    @RestResource(exported = false)
    Page<News> findAllBySourceAndLanguageAndCategory(String source, String language, String category, Pageable pageable);

    @RestResource(exported = false)
    Page<News> findAllByCategoryAndLanguage(String category, String language, Pageable pageable);
}
