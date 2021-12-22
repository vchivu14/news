package com.example.news.repos;

import com.example.news.entities.Source;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SourceRepo extends JpaRepository<Source,Integer> {
}
