package com.example.domain.config;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
@RequiredArgsConstructor
public class RabbitMqConfig {

    private final RabbitProperties rabbitProperties;

    // queue name
    @Value("${rabbitmq.queue1.name}")
    private String queue1;

    @Value("${rabbitmq.queue2.name}")
    private String queue2;

    @Value("${rabbitmq.queue3.name}")
    private String queue3;

    // exchange
    @Value("${rabbitmq.queue1.exchange}")
    private String exchange;

    @Value("${rabbitmq.routing.key.queue1}")
    private String routingKey1;

    @Value("${rabbitmq.routing.key.queue2}")
    private String routingKey2;

    @Value("${rabbitmq.routing.key.queue3}")
    private String routingKey3;

    @Bean
    Queue queue1() {
        return new Queue(queue1, true);
    }
    @Bean
    Queue queue2() {
        return new Queue(queue2, true);
    }
    @Bean
    Queue queue3() {
        return new Queue(queue3, true);
    }

    @Bean
    DirectExchange directExchange1() {
        return new DirectExchange(exchange);
    }

    @Bean
    DirectExchange directExchange2() {
        return new DirectExchange(exchange);
    }

    @Bean
    DirectExchange directExchange3() {
        return new DirectExchange(exchange);
    }

    @Bean
    Binding binding1() {
        return BindingBuilder.bind(queue1()).to(directExchange1()).with(routingKey1);
    }

    @Bean
    Binding binding2() {
        return BindingBuilder.bind(queue2()).to(directExchange2()).with(routingKey2);
    }

    @Bean
    public Binding binding3() {
        return BindingBuilder.bind(queue3()).to(directExchange3()).with(routingKey3);
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter messageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(rabbitProperties.getHost());
        connectionFactory.setPort(rabbitProperties.getPort());
        connectionFactory.setVirtualHost(rabbitProperties.getVirtualHost());
        connectionFactory.setUsername(rabbitProperties.getUsername());
        connectionFactory.setPassword(rabbitProperties.getPassword());
        return connectionFactory;
    }
}