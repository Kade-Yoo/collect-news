package com.example.domain.collect.dto;

import com.example.domain.collect.entity.News;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NewsResult {

    private String title;
    private String content;
    private String site;
    private String keyword;

    public NewsResult(News news) {
        this.title = news.getTitle();
        this.content = news.getContent();
        this.site = news.getSite().getSite();
        this.keyword = news.getKeyword().getKeyword();
    }
}
