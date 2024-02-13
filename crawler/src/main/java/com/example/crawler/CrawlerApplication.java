package com.example.crawler;

import com.example.crawler.service.DaumCrawler;
import com.example.crawler.service.NaverCrawler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication(scanBasePackages = {"com.example.crawler", "com.example.domain"})
public class CrawlerApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(CrawlerApplication.class, args);
    }
}
