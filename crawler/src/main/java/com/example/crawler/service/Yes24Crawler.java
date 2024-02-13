package com.example.crawler.service;

import com.example.crawler.common.enums.CrawlerType;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Component
public class Yes24Crawler implements Crawler {
    @Override
    public void crawling(String keyword) throws IOException {
        String url = createUrl(keyword);
        Document doc = Jsoup.connect(url).get();
        Elements headlines = doc.select(".itemUnit");

        for (Element headline : headlines) {
            String title = headline.select(".gd_name").text();
            String description = headline.select(".gd_nameE").text();
            String author = headline.select("a[href*=author]").text();
        }
    }

    @Override
    public String createUrl(String keyword) {
        keyword = URLEncoder.encode(keyword, StandardCharsets.UTF_8);
        return "https://www.yes24.com/Product/Search?domain=ALL&query=" + keyword + "&page=1&order=CONT_CNT";
    }

    @Override
    public CrawlerType getType() {
        return CrawlerType.YES24;
    }
}
