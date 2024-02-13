package com.example.crawler.service;

import com.example.crawler.common.enums.CrawlerType;
import com.example.crawler.dto.NewsRequest;
import com.example.domain.collect.entity.News;
import com.example.domain.collect.entity.SearchKeyword;
import com.example.domain.collect.entity.TargetSite;
import com.example.domain.collect.service.NewsService;
import com.example.domain.collect.service.SearchKeywordService;
import com.example.domain.collect.service.TargetSiteService;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DaumCrawler implements Crawler {

    private final SearchKeywordService searchKeywordService;
    private final TargetSiteService targetSiteService;
    private final NewsService newsService;

    @Override
    public void crawling(String keyword) throws IOException {
        String url = createUrl(keyword);
        Document doc = Jsoup.connect(url).get();
        Elements headlines = doc.select(".c-list-basic li");

        List<NewsRequest> newsRequests = new ArrayList<>();
        for (Element headline : headlines) {
            String agency = headline.select(".item-writer").text();
            String title = headline.select(".item-title").text();
            String content = headline.select(".conts-desc").text();
            String detailNewsUrl = headline.select(".thumb_bf").attr("href");

            newsRequests.add(new NewsRequest(title, content, agency, detailNewsUrl, keyword, "naver"));
        }

        // News 데이터 DB 저장
        List<News> newsList = newsRequests.stream().map(request -> {
            SearchKeyword byKeyword = searchKeywordService.getByKeyword(request.getKeyword());
            TargetSite bySite = targetSiteService.getBySite(request.getSite());
            return new News(
                    request.getTitle(),
                    request.getContent(),
                    request.getAgency(),
                    request.getDetailsNewsSite(),
                    bySite,
                    byKeyword);
        }).toList();
        newsService.saveAll(newsList);
    }

    @Override
    public String createUrl(String keyword) {
        keyword = URLEncoder.encode(keyword, StandardCharsets.UTF_8);
        return "https://search.daum.net/search?DA=YZR&q=" + keyword + "&spacing=&w=news&sort=recency";
    }

    @Override
    public CrawlerType getType() {
        return CrawlerType.DAUM;
    }
}
