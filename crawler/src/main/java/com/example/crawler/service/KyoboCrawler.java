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
public class KyoboCrawler implements Crawler {
    @Override
    public void crawling(String keyword) throws IOException {
        String url = createUrl(keyword);
        Document doc = Jsoup.connect(url).get();
        Elements headlines = doc.select(".prod_area");

        for (Element headline : headlines) {
            String title = headline.select("img").attr("data-kbbfn-title");
            String description = headline.select(".prod_desc").text();
            String author = headline.select(".author").text();
        }
    }

    @Override
    public String createUrl(String keyword) {
        keyword = URLEncoder.encode(keyword, StandardCharsets.UTF_8);
        return "https://search.kyobobook.co.kr/search?keyword=" + keyword + "&target=total&gbCode=TOT&ra=qntt";
    }

    @Override
    public CrawlerType getType() {
        return CrawlerType.KYOBO;
    }
}
