package com.example.news.configuration;

import com.example.news.dtos.NewsDTO;
import com.example.news.entities.News;
import com.example.news.services.NewsServerAPICall;
import com.example.news.services.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.sql.Timestamp;
import java.util.List;

//@Configuration
public class ScheduledTasks {

    @Autowired
    NewsService newsService;

    @Scheduled(fixedRate = 60*60*1000)
    public void getFreshNews() {
        NewsServerAPICall newsServerAPICall = new NewsServerAPICall();
        List<NewsDTO> newsDTOList = newsServerAPICall.fetchNewsList();
        List<News> news = newsService.getListOfNewsFromDTOs(newsDTOList);
        newsService.saveParameters(news);
        newsService.saveNews(news);
    }

    @Scheduled(fixedRate = 24*60*60*1000)
    public void removeOldNews() {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        newsService.removeNews(now);
        getFreshNews();
    }
}
