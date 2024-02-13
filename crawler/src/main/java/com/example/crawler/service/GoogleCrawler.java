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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class GoogleCrawler implements Crawler {

    private final SearchKeywordService searchKeywordService;
    private final TargetSiteService targetSiteService;
    private final NewsService newsService;

    public void crawling(String keyword) throws IOException {
        String url = createUrl(keyword);
        Document doc = Jsoup.connect(url).get();
        Elements newsElements = doc.select(".WlydOe");
        Elements newsTitleElements = doc.select("div[class=n0jPhd ynAwRc MBeuO nDgy9d]");

        List<NewsRequest> newsRequests = new ArrayList<>();
        for(Element element : newsTitleElements) {
            newsRequests.add(new NewsRequest(element.ownText()));
        }

        Elements newsContentElements = doc.select("div[class=GI74Re nDgy9d]");
        for (int index = 0; index < newsContentElements.size(); index++) {
            NewsRequest newsRequest = newsRequests.get(index);
            newsRequest.updateContent(newsContentElements.get(index).ownText());
        }

        for (int index = 0; index < newsElements.size(); index++) {
            NewsRequest newsRequest = newsRequests.get(index);
            Element element = newsElements.get(index);

            String agencyName = element.select("span").first().text();
            String detailNewsSite = element.select("a").attr("href");
            newsRequest.updateAddtionalInfo(agencyName, detailNewsSite, keyword, "google");
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

    /**
     * 뉴스 검색 url
     * TODO :정렬 추가 필요
     *
     * @param keyword 검색 키워드
     * @return 검색 url
     */
    @Override
    public String createUrl(String keyword) {
        String encodeKeyword = URLEncoder.encode(keyword, StandardCharsets.UTF_8);
        LocalDate now = LocalDate.now();
        String maxDate = now.getMonthValue() + "/" + now.getDayOfMonth() + "/" + now.getYear();
        LocalDate minLocalDate = now.minusMonths(12);
        String minDate = minLocalDate.getMonthValue() + "/" + minLocalDate.getDayOfMonth() + "/" + minLocalDate.getYear();

        return "https://www.google.com/search?q=" + encodeKeyword +"&hl=ko&tbm=nws&tbs=cdr:1,cd_min:" + minDate + ",cd_max:" + maxDate;
    }

    @Override
    public CrawlerType getType() {
        return CrawlerType.GOOGLE;
    }
}
