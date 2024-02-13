package com.example.domain.collect.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class SearchKeyword {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long searchKewordId;

    private String keyword;

    public SearchKeyword() {}
    public SearchKeyword(String keyword) {
        this.keyword = keyword;
    }
}
