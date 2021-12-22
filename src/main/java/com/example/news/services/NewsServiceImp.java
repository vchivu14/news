package com.example.news.services;

import com.example.news.dtos.NewsDTO;
import com.example.news.dtos.Parameters;
import com.example.news.entities.*;
import com.example.news.repos.*;
import com.example.news.utils.Utils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
public class NewsServiceImp implements NewsService {
    private NewsRepository newsRepository;

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

    public NewsServiceImp(NewsRepository newsRepository, AuthorRepo authorRepo,
                          CategoryRepo categoryRepo, CountryRepo countryRepo,
                          LanguageRepo languageRepo, SourceRepo sourceRepo) {
        this.newsRepository = newsRepository;
        this.authorRepo = authorRepo;
        this.categoryRepo = categoryRepo;
        this.countryRepo = countryRepo;
        this.languageRepo = languageRepo;
        this.sourceRepo = sourceRepo;
    }

    @Override
    public List<News> getAll() {
        return newsRepository.findAll();
    }

    @Override
    public List<News> getAll(String title) {
        return newsRepository.findAllByTitleContaining(title);
    }

    private Page<News> filterParameters(String author, String category, String language, String source, Pageable paging) {
        Page<News> pagedNews = null;
        if (!author.equals("null") && !category.equals("null") && !language.equals("null") && !source.equals("null")) {
            pagedNews = newsRepository.findAllByAuthorAndSourceAndLanguageAndCategory(author,source,language,category,paging);
        } else if (!author.equals("null") && !category.equals("null") && !language.equals("null")) {
            pagedNews = newsRepository.findAllByAuthorAndLanguageAndCategory(author,language,category,paging);
        } else if (!author.equals("null") && !category.equals("null") && !source.equals("null")) {
            pagedNews = newsRepository.findAllByAuthorAndSourceAndCategory(author,source,category,paging);
        } else if (!author.equals("null") && !language.equals("null") && !source.equals("null")) {
            pagedNews = newsRepository.findAllByAuthorAndSourceAndLanguage(author,source,language,paging);
        } else if (!category.equals("null") && !language.equals("null") && !source.equals("null")) {
            pagedNews = newsRepository.findAllBySourceAndLanguageAndCategory(source,language,category,paging);
        } else if (!author.equals("null") && !category.equals("null")) {
            pagedNews = newsRepository.findAllByAuthorAndCategory(author,category,paging);
        } else if (!author.equals("null") && !language.equals("null")) {
            pagedNews = newsRepository.findAllByAuthorAndLanguage(author,language,paging);
        } else if (!author.equals("null") && !source.equals("null")) {
            pagedNews = newsRepository.findAllByAuthorAndSource(author,source,paging);
        } else if (!category.equals("null") && !language.equals("null")) {
            pagedNews = newsRepository.findAllByCategoryAndLanguage(category,language,paging);
        } else if (!category.equals("null") && !source.equals("null")) {
            pagedNews = newsRepository.findAllBySourceAndCategory(source,category,paging);
        } else if (!source.equals("null") && !language.equals("null")) {
            pagedNews = newsRepository.findAllBySourceAndLanguage(source,language,paging);
        } else if (!author.equals("null")) {
            pagedNews = newsRepository.findAllByAuthor(author, paging);
        } else if (!category.equals("null")) {
            pagedNews = newsRepository.findAllByCategory(category, paging);
        } else if (!source.equals("null")) {
            pagedNews = newsRepository.findAllBySource(source,paging);
        } else if (!language.equals("null")) {
            pagedNews = newsRepository.findAllByLanguage(language,paging);
        }
        return pagedNews;
    }

    private Map<String, Object> getStringObjectMap(Page<News> pagedNews) {
        List<News> news = pagedNews.getContent();
        List<NewsDTO> newsDTOS = getListOfNewsDTOFromNews(news);
        Map<String, Object> response = new HashMap<>();
        response.put("news", newsDTOS);
        response.put("firstPage", pagedNews.get().findFirst());
        response.put("currentPage", pagedNews.getNumber());
        response.put("totalItems", pagedNews.getTotalElements());
        response.put("totalPages", pagedNews.getTotalPages());
        return response;
    }

    private Map<String, Object> getResponseMap(String title, Pageable paging) {
        Page<News> pagedNews;
        if (title == null) {
            pagedNews = newsRepository.findAll(paging);
        } else {
            pagedNews = newsRepository.findByTitleContaining(title, paging);
        }
        return getStringObjectMap(pagedNews);
    }

    @Override
    public List<NewsDTO> getAllSorted(Map<String, Object> response) {
        return (List<NewsDTO>) response.get("news");
    }
    @Override
    public Object getFirstPage(Map<String, Object> response) {
        return response.get("firstPage");
    }
    @Override
    public Integer getCurrentPage (Map<String, Object> response) {
        return (Integer) response.get("currentPage");
    }
    @Override
    public Object getTotalItems (Map<String, Object> response) {
        return response.get("totalItems");
    }
    @Override
    public Integer getTotalPages (Map<String, Object> response) {
        return (Integer) response.get("totalPages");
    }

    @Override
    public Map<String, Object> getAllSorted(String author, String category, String language, String source,
                                            int page, int size, String sort) {
        Pageable paging;
        if (sort.equals("null")) {
            paging = PageRequest.of(page, size);
        } else {
            paging = PageRequest.of(page, size, Sort.by(sort));
        }
        Page<News> pagedNews;
        if (author.equals("null") && category.equals("null") && language.equals("null") && source.equals("null")) {
            pagedNews = newsRepository.findAll(paging);
        } else {
            pagedNews = filterParameters(author,category,language,source,paging);
        }
        return getStringObjectMap(pagedNews);
    }

    @Override
    public Map<String, Object> getAll(String title, int page, int size) {
        Pageable paging = PageRequest.of(page, size);
        return getResponseMap(title, paging);
    }

    @Override
    public Map<String, Object> getAll(String title, int page, int size, String sort) {
        Pageable paging;
        if (sort == null) {
            paging = PageRequest.of(page, size);
        } else {
            paging = PageRequest.of(page, size, Sort.by(sort));
        }
        return getResponseMap(title, paging);
    }

    private News mapNewsDTOtoNews(NewsDTO newsDTO) {
        News news = new News();
        news.setAuthor(newsDTO.getAuthor());
        news.setTitle(newsDTO.getTitle());
        news.setCategory(newsDTO.getCategory());
        news.setCountry(newsDTO.getCountry());
        news.setSource(newsDTO.getSource());
        news.setImage(newsDTO.getImage());
        news.setUrl(newsDTO.getUri());
        news.setLanguage(newsDTO.getLanguage());
        news.setDescription(newsDTO.getDescription());
        news.setPublished(Utils.getTimestampFromString(newsDTO.getPublished_at()));
        return news;
    }
    @Override
    public List<News> getListOfNewsFromDTOs(List<NewsDTO> newsDTOList) {
        List<News> news = new ArrayList<>();
        for (NewsDTO n: newsDTOList) {
            news.add(mapNewsDTOtoNews(n));
        }
        return news;
    }
    private NewsDTO mapNewsToNewsDTO(News news) {
        NewsDTO newsDTO = new NewsDTO();
        newsDTO.setAuthor(news.getAuthor());
        newsDTO.setTitle(news.getTitle());
        newsDTO.setCategory(news.getCategory());
        newsDTO.setCountry(news.getCountry());
        newsDTO.setSource(news.getSource());
        newsDTO.setImage(news.getImage());
        newsDTO.setUri(news.getUrl());
        newsDTO.setLanguage(news.getLanguage());
        newsDTO.setDescription(news.getDescription());
        newsDTO.setPublished_at(String.valueOf(news.getPublished()));
        return newsDTO;
    }
    @Override
    public List<NewsDTO> getListOfNewsDTOFromNews(List<News> news) {
        List<NewsDTO> newsDTOS = new ArrayList<>();
        for (News n: news) {
            newsDTOS.add(mapNewsToNewsDTO(n));
        }
        return newsDTOS;
    }

    @Override
    public void saveNews(List<News> news) {
        newsRepository.saveAll(news);
    }

    @Override
    public void removeNews(Timestamp time) {
//        Date now = new Timestamp(System.currentTimeMillis());
//        LocalDateTime yesterday = LocalDateTime.from((TemporalAccessor) now).minusDays(1);
//        List<News> news = newsRepository.findAllByPublished_atIs(Utils.getDateString(yesterday));
//        newsRepository.deleteAll(news);
    }

    @Override
    public void saveFromServer(Parameters parameters) {
        NewsServerAPICall newsServerAPICall = new NewsServerAPICall(parameters);
        List<NewsDTO> newsDTOS = newsServerAPICall.fetchNewsList();
        List<News> news = getListOfNewsFromDTOs(newsDTOS);
        saveParameters(news);
        newsRepository.deleteAll();
        newsRepository.saveAll(news);
    }

    @Override
    public void saveFromServer() {
        NewsServerAPICall newsServerAPICall = new NewsServerAPICall();
        List<NewsDTO> newsDTOS = newsServerAPICall.fetchNewsList();
        if (newsDTOS.size() != 0) {

        }
        List<News> news = getListOfNewsFromDTOs(newsDTOS);
        saveParameters(news);
        newsRepository.deleteAll();
        newsRepository.saveAll(news);
    }

    @Override
    public void saveParameters(List<News> news) {
        Set<String> authors = new HashSet<>();
        Set<String> categories = new HashSet<>();
        Set<String> countries = new HashSet<>();
        Set<String> languages = new HashSet<>();
        Set<String> sources = new HashSet<>();
        for (News n: news) {
            if (n.getAuthor() != null) {
                authors.add(n.getAuthor());
            }
            if (n.getCategory() != null) {
                categories.add(n.getCategory());
            }
            if (n.getCountry() != null) {
                countries.add(n.getCountry());
            }
            if (n.getLanguage() != null) {
                languages.add(n.getLanguage());
            }
            if (n.getSource() != null) {
                sources.add(n.getSource());
            }
        }
        if (authors.size() > 0) {
            List<String> authorsList = new ArrayList<>(authors);
            List<Author> authorsToSave = new ArrayList<>();
            for (String a: authorsList) {
                authorsToSave.add(new Author(a));
            }

            authorRepo.deleteAll();
            authorRepo.saveAll(authorsToSave);
        }

        if (categories.size() > 0) {
            for (String c: categories) {
                if (categoryRepo.findByName(c) == null) {
                    categoryRepo.save(new Category(c));
                }
            }
        }

        if (countries.size() > 0) {
            for (String c: countries) {
                if (countryRepo.findByName(c) == null) {
                    countryRepo.save(new Country(c));
                }
            }
        }

        if (languages.size() > 0) {
            for (String l: languages) {
                if (languageRepo.findByName(l) == null) {
                    languageRepo.save(new Language(l));
                }
            }
        }

        if (sources.size() > 0) {
            List<String> sourcesList = new ArrayList<>(sources);
            List<Source> sourcesToSave = new ArrayList<>();
            for (String s: sourcesList) {
                sourcesToSave.add(new Source(s));
            }
            sourceRepo.deleteAll();
            sourceRepo.saveAll(sourcesToSave);
        }

    }
}
