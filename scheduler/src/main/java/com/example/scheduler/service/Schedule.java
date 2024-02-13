package com.example.scheduler.service;

import ch.qos.logback.core.pattern.parser.SimpleKeywordNode;
import com.example.domain.collect.service.SearchKeywordService;
import com.example.scheduler.dto.MessageCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class Schedule {
    private final RabbitTemplate rabbitTemplate;
    private final SearchKeywordService searchKeywordService;

    @Scheduled(cron = "0 0 * * * *")
    public void crawler() {
        List<String> allKeyword = searchKeywordService.getAllKeyword();
        allKeyword.forEach(keyword -> sendMessage(new MessageCommand(keyword)));
    }

    @Async
    public void sendMessage(MessageCommand command) {
        try {
            rabbitTemplate.convertAndSend("collect-exchange", "collect-mq1", command);
        } catch (Exception jpe) {
            System.out.println("파싱 오류 발생");
        }
    }
}
