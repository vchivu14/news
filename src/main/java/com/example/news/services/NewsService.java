package com.example.news.services;

import com.example.news.dtos.NewsDTO;
import com.example.news.dtos.Parameters;
import com.example.news.entities.News;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public interface NewsService {
    List<News> getAll();
    List<News> getAll(String title);
    Map<String, Object> getAll(String title, int page, int size);
    Map<String, Object> getAll(String title, int page, int size, String sort);
    Map<String, Object> getAllSorted(String author, String category, String language, String source,
                                     int page, int size, String sort);

    List<NewsDTO> getAllSorted(Map<String, Object> response);
    Object getFirstPage(Map<String, Object> response);
    Integer getCurrentPage (Map<String, Object> response);
    Object getTotalItems (Map<String, Object> response);
    Integer getTotalPages (Map<String, Object> response);

    List<News> getListOfNewsFromDTOs(List<NewsDTO> newsDTOList);
    List<NewsDTO> getListOfNewsDTOFromNews(List<News> news);

    void saveNews(List<News> news);
    void removeNews(Timestamp timestamp);
    void saveFromServer(Parameters parameters);
    void saveFromServer();
    void saveParameters(List<News> news);
}
