package com.example.news.configuration;

import com.example.news.entities.Category;
import com.example.news.entities.Country;
import com.example.news.entities.Language;
import com.example.news.repos.CategoryRepo;
import com.example.news.repos.CountryRepo;
import com.example.news.repos.LanguageRepo;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {
    private CategoryRepo categoryRepo;
    private CountryRepo countryRepo;
    private LanguageRepo languageRepo;

    public DataLoader(CategoryRepo categoryRepo, CountryRepo countryRepo,
                      LanguageRepo languageRepo) {
        this.categoryRepo = categoryRepo;
        this.countryRepo = countryRepo;
        this.languageRepo = languageRepo;
        if (categoryRepo.count() == 0) {
            loadCategories();
        }
        if (countryRepo.count() == 0) {
            loadCountries();
        }
        if (languageRepo.count() == 0) {
            loadLanguages();
        }
    }

    private void loadCategories() {
        categoryRepo.save(new Category("general"));
        categoryRepo.save(new Category("business"));
        categoryRepo.save(new Category("sports"));
    }

    private void loadCountries() {
        countryRepo.save(new Country("sa"));
        countryRepo.save(new Country("de"));
        countryRepo.save(new Country("gb"));
        countryRepo.save(new Country("us"));
        countryRepo.save(new Country("ca"));
        countryRepo.save(new Country("fr"));
        countryRepo.save(new Country("au"));
        countryRepo.save(new Country("il"));
        countryRepo.save(new Country("it"));
        countryRepo.save(new Country("nl"));
        countryRepo.save(new Country("no"));
        countryRepo.save(new Country("pt"));
        countryRepo.save(new Country("se"));
        countryRepo.save(new Country("cn"));
    }

    private void loadLanguages() {
        languageRepo.save(new Language("ar"));
        languageRepo.save(new Language("de"));
        languageRepo.save(new Language("en"));
        languageRepo.save(new Language("es"));
        languageRepo.save(new Language("fr"));
        languageRepo.save(new Language("he"));
        languageRepo.save(new Language("it"));
        languageRepo.save(new Language("nl"));
        languageRepo.save(new Language("no"));
        languageRepo.save(new Language("pt"));
        languageRepo.save(new Language("ru"));
        languageRepo.save(new Language("se"));
        languageRepo.save(new Language("zh"));
    }
}
