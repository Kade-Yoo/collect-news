package com.example.application.view.controller;

import com.example.application.api.collect.dto.NewsResponse;
import com.example.application.api.collect.usecase.CollectUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/view")
public class CollectViewController {

    private final CollectUsecase collectUsecase;

    @GetMapping("/settings")
    public String viewSettings() {
        return "settings";
    }

    @GetMapping("/news")
    public String viewNews(Model model) {
        List<NewsResponse> allNews = collectUsecase.getAllNews();
        if (!allNews.isEmpty()) {
            model.addAttribute("news", allNews);
        }
        return "news_view";
    }
}
