package com.example.news.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode
public class NewsObjectDTO {
    private Pagination pagination;
    private NewsDTO[] data;
}



