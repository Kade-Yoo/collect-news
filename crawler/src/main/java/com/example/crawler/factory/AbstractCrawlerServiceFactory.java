package com.example.crawler.factory;

import com.example.crawler.service.Crawler;

abstract public class AbstractCrawlerServiceFactory {

    abstract public Crawler getCrawler(String site);
}
