package com.example.crawler.service;

import com.example.crawler.common.enums.CrawlerType;

import java.io.IOException;

public interface Crawler {
    void crawling(String keyword) throws IOException;
    String createUrl(String keyword);
    CrawlerType getType();
}
