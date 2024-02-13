package com.example.crawler.common.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
public enum CrawlerType {
    NAVER("naver"),
    DAUM("daum"),
    GOOGLE("google"),
    YES24("yes24"),
    KYOBO("kyobo"),
    ALADIN("aladin"),
    NONE("");

    private final String siteName;

    CrawlerType(String siteName) {
        this.siteName = siteName;
    }

    private static final Map<String, CrawlerType> crawlerType;

    static {
        Map<String, CrawlerType> map = Arrays.stream(values())
                .collect(Collectors.toMap(CrawlerType::getSiteName, Function.identity()));
        crawlerType = map;
    }

    public static CrawlerType findBySiteName(String siteName) {
        return crawlerType.getOrDefault(siteName, NONE);
    }
}
