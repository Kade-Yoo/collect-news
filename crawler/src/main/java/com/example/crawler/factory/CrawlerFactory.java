package com.example.crawler.factory;

import com.example.crawler.common.enums.CrawlerType;
import com.example.crawler.service.Crawler;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CrawlerFactory extends AbstractCrawlerServiceFactory {

    private final Map<CrawlerType, Crawler> crawlerServiceMap = new HashMap<>();

    public CrawlerFactory(List<Crawler> crawlerList) {
        crawlerList.forEach(service -> crawlerServiceMap.put(service.getType(), service));
    }
    @Override
    public Crawler getCrawler(String site) {
        return crawlerServiceMap.get(CrawlerType.findBySiteName(site));
    }
}
