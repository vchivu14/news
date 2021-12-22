package com.example.news.repos;

import com.example.news.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Integer> {
    Category findByName(String name);
}
