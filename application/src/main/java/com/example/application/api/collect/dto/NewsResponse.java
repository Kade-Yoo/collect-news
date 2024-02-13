package com.example.application.api.collect.dto;

import com.example.domain.collect.dto.NewsResult;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NewsResponse {

    private String title;
    private String content;
    private String site;
    private String keyword;

    public NewsResponse(NewsResult newsResult) {
        this.title = newsResult.getTitle();
        this.content = newsResult.getContent();
        this.site = newsResult.getSite();
        this.keyword = newsResult.getKeyword();
    }
}
