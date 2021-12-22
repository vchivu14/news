package com.example.news.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode
public class NewsDTO {
    private String author;
    private String title;
    private String description;
    private String uri;
    private String source;
    private String image;
    private String category;
    private String language;
    private String country;
    private String published_at;
}
