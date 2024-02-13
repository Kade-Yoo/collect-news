package com.example.crawler.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MessageCommand {

    private String title;
    private String message;

    public MessageCommand(String title, String message) {
        this.title = title;
        this.message = message;
    }
}
