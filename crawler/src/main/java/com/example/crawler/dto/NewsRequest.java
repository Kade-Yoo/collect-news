package com.example.crawler.dto;

import lombok.Getter;

@Getter
public class NewsRequest {

    private String title;
    private String content;
    private String agency;
    private String detailsNewsSite;
    private String keyword;
    private String site;

    public NewsRequest(String title) {
        this.title = title;
    }

    public NewsRequest(String title, String content, String agency, String detailsNewsSite, String keyword, String site) {
        this.title = title;
        this.content = content;
        this.agency = agency;
        this.detailsNewsSite = detailsNewsSite;
        this.keyword = keyword;
        this.site = site;
    }

    public void updateContent(String content) {
        this.content = content;
    }

    public void updateAddtionalInfo(String agency, String detailsNewsSite, String keyword, String site) {
        this.agency = agency;
        this.detailsNewsSite = detailsNewsSite;
        this.keyword = keyword;
        this.site = site;
    }
}
