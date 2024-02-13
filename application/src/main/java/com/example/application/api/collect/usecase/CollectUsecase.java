package com.example.application.api.collect.usecase;

import com.example.application.api.collect.dto.NewsResponse;
import com.example.application.api.collect.dto.SearchKeywordRequest;
import com.example.application.api.collect.dto.SettingRequest;
import com.example.domain.collect.dto.MessageCommand;
import com.example.domain.collect.dto.SearchKeywordCommand;
import com.example.domain.collect.service.NewsService;
import com.example.domain.collect.service.ProducerService;
import com.example.domain.collect.service.SearchKeywordService;
import com.example.domain.collect.service.TargetSiteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CollectUsecase {

    private final SearchKeywordService searchKeywordService;
    private final TargetSiteService targetSiteService;
    private final ProducerService producerService;
    private final NewsService newsService;

    public void saveSettings(SettingRequest request) {
        targetSiteService.save(request.getSite());
        this.saveSearchKewyord(request.getKeyword());
    }

    public void saveSearchKewyord(String keyword) {
        SearchKeywordCommand searchKeywordCommand = new SearchKeywordCommand(keyword);
        searchKeywordService.save(searchKeywordCommand);

        producerService.sendMessage(MessageCommand.builder()
                        .message(keyword).build()
        );
    }

    public List<String> getAllKeyword() {
        return searchKeywordService.getAllKeyword();
    }
    public List<String> getAllTargetSite() {
        return targetSiteService.findAllTargetSite();
    }
    public List<NewsResponse> getAllNews() {
        return newsService.findAll().stream().map(NewsResponse::new).toList();
    }

    public void saveNews(String title) {
        newsService.save(title);
    }
}
