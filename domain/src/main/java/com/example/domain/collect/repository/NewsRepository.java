package com.example.domain.collect.repository;

import com.example.domain.collect.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, Long> {
}
