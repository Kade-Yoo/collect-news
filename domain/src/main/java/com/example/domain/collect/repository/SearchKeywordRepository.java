package com.example.domain.collect.repository;

import com.example.domain.collect.entity.SearchKeyword;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SearchKeywordRepository extends JpaRepository<SearchKeyword, Long> {

    boolean existsByKeyword(String keyword);
    SearchKeyword findByKeyword(String keyword);
}
