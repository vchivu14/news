package com.example.news.controllers;

import com.example.news.dtos.NewsDTO;
import com.example.news.dtos.Parameters;
import com.example.news.repos.*;
import com.example.news.services.NewsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/")
public class NewsController {
    private NewsService newsService;

    final
    AuthorRepo authorRepo;
    final
    CategoryRepo categoryRepo;
    final
    CountryRepo countryRepo;
    final
    LanguageRepo languageRepo;
    final
    SourceRepo sourceRepo;

    public NewsController(NewsService newsService, AuthorRepo authorRepo,
                          CategoryRepo categoryRepo, CountryRepo countryRepo,
                          LanguageRepo languageRepo, SourceRepo sourceRepo) {
        this.newsService = newsService;
        this.authorRepo = authorRepo;
        this.categoryRepo = categoryRepo;
        this.countryRepo = countryRepo;
        this.languageRepo = languageRepo;
        this.sourceRepo = sourceRepo;
    }

    @ModelAttribute("parameters")
    public Parameters getParametersEmpty() {
        return new Parameters();
    }

    private void extractDetailsPage(Model model, Map<String, Object> map) {
        model.addAttribute("newsList", newsService.getAllSorted(map));
        model.addAttribute("firstPage", newsService.getFirstPage(map));
        model.addAttribute("currentPage", newsService.getCurrentPage(map));
        model.addAttribute("totalItems", newsService.getTotalItems(map));
        model.addAttribute("totalPages", newsService.getTotalPages(map));
    }

    @GetMapping
    public String getHomePage(@ModelAttribute Parameters parameters,
                              Model model) {
        model.addAttribute("authors", authorRepo.findAll());
        model.addAttribute("categories", categoryRepo.findAll());
        model.addAttribute("countries", countryRepo.findAll());
        model.addAttribute("languages", languageRepo.findAll());
        model.addAttribute("sources", sourceRepo.findAll());
        model.addAttribute(parameters);
        return "home";
    }

    @GetMapping("/news")
    public String getNews(@RequestParam(required = false, defaultValue = "false") String defaultNews,
                          @RequestParam(required = false) String title,
                          @RequestParam(defaultValue = "0") Integer page,
                          @RequestParam(defaultValue = "5") Integer size,
                          @RequestParam(required = false, defaultValue = "published") String sort,
                          @ModelAttribute NewsDTO newsDTO,
                          Model model) {
        model.addAttribute(newsDTO);
        Map<String, Object> map;
        if (defaultNews.equals("true")) {
            newsService.saveFromServer();
            map = newsService.getAll(title,page,size);
        } else {
            map = newsService.getAll(title,page,size,sort);
        }
        extractDetailsPage(model, map);
        model.addAttribute("authors", authorRepo.findAll());
        model.addAttribute("categories", categoryRepo.findAll());
        model.addAttribute("countries", countryRepo.findAll());
        model.addAttribute("languages", languageRepo.findAll());
        model.addAttribute("sources", sourceRepo.findAll());
        return "news";
    }

    @PostMapping("/mynews")
    public String getNewsCustom(@ModelAttribute Parameters parameters) {
        newsService.saveFromServer(parameters);
        return "redirect:/news";
    }

    @PostMapping("/sorted")
    public String getNewsSorted(@RequestParam(required = false, defaultValue = "false") String author,
                                @RequestParam(required = false, defaultValue = "false") String category,
                                @RequestParam(required = false, defaultValue = "false") String language,
                                @RequestParam(required = false, defaultValue = "false") String source,
                                @RequestParam(defaultValue = "0") Integer page,
                                @RequestParam(defaultValue = "5") Integer size,
                                @RequestParam(required = false, defaultValue = "null") String sort,
                                @ModelAttribute NewsDTO newsDTO,
                                Model model) {
        model.addAttribute(newsDTO);
        Map<String, Object> map = newsService.getAllSorted(author,category,language,source,page,size,sort);
        extractDetailsPage(model, map);
        return "news";
    }

}
