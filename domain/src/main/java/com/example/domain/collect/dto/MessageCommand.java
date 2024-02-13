package com.example.domain.collect.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MessageCommand {
    private String title;
    private String message;

    @Builder
    public MessageCommand(String title, String message) {
        this.title = title;
        this.message = message;
    }
}
