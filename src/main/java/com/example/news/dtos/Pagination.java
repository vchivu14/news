package com.example.news.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode
class Pagination {
    private Integer limit;
    private Integer offset;
    private Integer count;
    private Integer total;
}