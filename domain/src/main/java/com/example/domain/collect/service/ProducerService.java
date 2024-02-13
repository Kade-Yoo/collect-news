package com.example.domain.collect.service;

import com.example.domain.collect.dto.MessageCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProducerService {

    private final RabbitTemplate rabbitTemplate;

    @Async
    public void sendMessage(MessageCommand command) {
        try {
            rabbitTemplate.convertAndSend("collect-exchange", "collect-mq1", command);
        } catch (Exception jpe) {
            System.out.println("파싱 오류 발생");
        }
    }
}
