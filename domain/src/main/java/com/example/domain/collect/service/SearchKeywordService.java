package com.example.domain.collect.service;

import com.example.domain.collect.dto.SearchKeywordCommand;
import com.example.domain.collect.dto.SearchKeywordResult;
import com.example.domain.collect.entity.SearchKeyword;
import com.example.domain.collect.repository.SearchKeywordRepository;
import com.example.domain.common.ErrorCode;
import com.example.domain.common.InvalidInputException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SearchKeywordService {

    private final SearchKeywordRepository searchKeywordRepository;

    public void save(SearchKeywordCommand command) {
        boolean byKeyword = searchKeywordRepository.existsByKeyword(command.getKeyword());
        if (byKeyword) {
            throw new InvalidInputException(ErrorCode.EXISTS_KEYWORD);
        }

        SearchKeyword searchKeyword = new SearchKeyword(command.getKeyword());
        searchKeywordRepository.save(searchKeyword);
    }

    public List<String> getAllKeyword() {
        return searchKeywordRepository.findAll().stream().map(SearchKeyword::getKeyword).toList();
    }

    public SearchKeyword getByKeyword(String keyword) {
        return searchKeywordRepository.findByKeyword(keyword);
    }
}
