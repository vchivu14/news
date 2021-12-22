package com.example.news.repos;

import com.example.news.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepo extends JpaRepository<Country,Integer> {
    Country findByName(String name);
}
