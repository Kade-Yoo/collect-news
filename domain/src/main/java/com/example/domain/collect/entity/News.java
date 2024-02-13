package com.example.domain.collect.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "int unsigned", nullable = false)
    private Long newsId;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private String agency;

    @Column
    private String detailNewsSite;

    @ManyToOne
    @JoinColumn(referencedColumnName = "targetSiteId", name = "site")
    private TargetSite site;

    @ManyToOne
    @JoinColumn(referencedColumnName = "searchKewordId", name = "keyword")
    private SearchKeyword keyword;

    public News(String title, String content, String agency, String detailNewsSite, TargetSite site, SearchKeyword keyword) {
        this.title = title;
        this.content = content;
        this.agency = agency;
        this.detailNewsSite = detailNewsSite;
        this.site = site;
        this.keyword = keyword;
    }

    public News(String title) {
        this.title = title;
    }
}
