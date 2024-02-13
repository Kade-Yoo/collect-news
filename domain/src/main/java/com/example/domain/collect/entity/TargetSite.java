package com.example.domain.collect.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class TargetSite {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long targetSiteId;

    @Column
    private String site;

    public TargetSite(String site) {
        this.site = site;
    }
}