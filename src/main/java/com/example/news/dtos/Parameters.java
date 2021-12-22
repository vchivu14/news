package com.example.news.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class Parameters {
    private String sources;
    private String categories;
    private String countries;
    private String languages;
    private String keywords;
    private String date;
    private String sort;

    public Parameters(String nothing) {
        this.sources = nothing;
        this.categories = nothing;
        this.countries = nothing;
        this.languages = nothing;
        this.keywords = nothing;
        this.date = nothing;
        this.sort = nothing;
    }
}
