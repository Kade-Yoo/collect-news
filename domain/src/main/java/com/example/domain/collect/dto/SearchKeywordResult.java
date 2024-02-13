package com.example.domain.collect.dto;

import com.example.domain.collect.entity.SearchKeyword;
import lombok.Getter;

@Getter
public class SearchKeywordResult {

    private Long searchKeywordId;
    private String searchKeyword;

    public SearchKeywordResult(Long searchKeywordId, String searchKeyword) {
        this.searchKeywordId = searchKeywordId;
        this.searchKeyword = searchKeyword;
    }

    public SearchKeywordResult(SearchKeyword entity) {
        this.searchKeywordId = entity.getSearchKewordId();
        this.searchKeyword = entity.getKeyword();
    }
}
