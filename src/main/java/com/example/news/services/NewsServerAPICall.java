package com.example.news.services;

import com.example.news.dtos.Parameters;
import com.example.news.utils.Utils;
import com.example.news.dtos.NewsDTO;
import com.example.news.dtos.NewsObjectDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;

@Service
public class NewsServerAPICall {
    private final String API_POINT = "http://api.mediastack.com/v1/news?access_key=";
    private final String key = "1266899774031f73d88da46f5d93aac2";

    private WebClient webClient;

    public NewsServerAPICall(Parameters parameters) {
        String parametersAsked = "";
        if (!parameters.getSources().equals("null")) {
            parametersAsked += "&sources="+parameters.getSources();
        }
        if (!parameters.getCategories().equals("null")) {
            parametersAsked += "&categories="+parameters.getCategories();
        }
        if (!parameters.getCountries().equals("null")) {
            parametersAsked += "&countries="+parameters.getCountries();
        }
        if (!parameters.getLanguages().equals("null")) {
            parametersAsked += "&languages="+parameters.getLanguages();
        }
        if (parameters.getKeywords() != null) {
            parametersAsked += "&keywords="+parameters.getKeywords();
        }
        if (parameters.getDate() != null) {
            parametersAsked += "&date="+parameters.getDate();
        }
        if (!parameters.getSort().equals("null")) {
            parametersAsked += "&sort="+parameters.getSort();
        }
        this.webClient = WebClient.builder()
                .baseUrl(API_POINT + key + parametersAsked)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public NewsServerAPICall() {
        this.webClient = WebClient.builder()
                .baseUrl(API_POINT + key)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
        String limit = "&limit=100";
        String language = "&languages=en";
        String dateToday = "&dateToday="+ Utils.getDateToday();
        this.webClient = WebClient.builder()
                .baseUrl(API_POINT+key+language+dateToday+limit)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public List<NewsDTO> fetchNewsList() {
        NewsObjectDTO response = this.webClient.get()
                .retrieve()
                .bodyToMono(NewsObjectDTO.class)
                .block();
        return List.of(response.getData());
    }

}