package com.example.domain.collect.service;

import com.example.domain.collect.dto.NewsResult;
import com.example.domain.collect.entity.News;
import com.example.domain.collect.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class NewsService {

    private final NewsRepository newsRepository;

    public void save(String title) {
        News news = new News(title);
        newsRepository.save(news);
    }

    public void saveAll(List<News> news) {
        newsRepository.saveAll(news);
    }

    public List<NewsResult> findAll() {
        return newsRepository.findAll().stream().map(NewsResult::new).toList();
    }
}
