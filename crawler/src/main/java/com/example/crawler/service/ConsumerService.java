package com.example.crawler.service;

import com.example.crawler.dto.MessageCommand;
import com.example.crawler.factory.CrawlerFactory;
import com.example.domain.collect.service.TargetSiteService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConsumerService {

    private final TargetSiteService targetSiteService;
    private final CrawlerFactory crawlerFactory;

    @RabbitListener(queues = "collect-mq1")
    public void receiveMessage(Message message) throws IOException {
        String string = new String(message.getBody());
        MessageCommand command = new Gson().fromJson(string, MessageCommand.class);

        List<String> allTargetSite = targetSiteService.findAllTargetSite();
        for (String site : allTargetSite) {
            Crawler crawler = crawlerFactory.getCrawler(site);
            crawler.crawling(command.getMessage());
        }
    }
}
