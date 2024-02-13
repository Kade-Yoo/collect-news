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
public class AladinCrawler implements Crawler {
    @Override
    public void crawling(String keyword) throws IOException {
        String url = createUrl(keyword);
        Document doc = Jsoup.connect(url).get();
        Elements headlines = doc.select(".ss_book_box");

        for (Element headline : headlines) {
            String title = headline.select(".bo3").text();
            String description = headline.select(".ss_f_g2").text();
            String author = headline.select("a[href*=AuthorSearch]").text();
        }
    }

    @Override
    public String createUrl(String keyword) {
        keyword = URLEncoder.encode(keyword, StandardCharsets.UTF_8);
        return "https://www.aladin.co.kr/search/wsearchresult.aspx?SearchTarget=All&KeyWord=" + keyword
                + "&KeyRecentPublish=0&OutStock=0&ViewType=Detail&SortOrder=2&CustReviewCount=0&CustReviewRank=0&CategorySearch=&chkKeyTitle=&chkKeyAuthor=&chkKeyPublisher=&chkKeyISBN=&chkKeyTag=&chkKeyTOC=&chkKeySubject=&ViewRowCount=25&SuggestKeyWord=";
    }

    @Override
    public CrawlerType getType() {
        return CrawlerType.ALADIN;
    }
}
