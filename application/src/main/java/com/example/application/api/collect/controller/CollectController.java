package com.example.application.api.collect.controller;

import com.example.application.api.collect.dto.NewsResponse;
import com.example.application.api.collect.dto.SearchKeywordRequest;
import com.example.application.api.collect.dto.SettingRequest;
import com.example.application.api.collect.usecase.CollectUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CollectController {

    private final CollectUsecase collectUsecase;

    @PostMapping("/search-keyword")
    public ResponseEntity<Void> save(@RequestBody SearchKeywordRequest request) {
        collectUsecase.saveSearchKewyord(request.getKeyword());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search-keyword/list")
    public ResponseEntity<List<String>> getAllKeyword() {
        List<String> all = collectUsecase.getAllKeyword();
        return ResponseEntity.ok(all);
    }

    @GetMapping("/target-site/list")
    public ResponseEntity<List<String>> getAllTargetSite() {
        List<String> all = collectUsecase.getAllTargetSite();
        return ResponseEntity.ok(all);
    }

    @PostMapping("/news")
    public ResponseEntity<Void> saveNews(@RequestBody String news) {
        collectUsecase.saveNews(news);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/settings")
    public ResponseEntity<Void> saveSettings(@RequestBody SettingRequest request) {
        collectUsecase.saveSettings(request);
        return ResponseEntity.ok().build();
    }
}
