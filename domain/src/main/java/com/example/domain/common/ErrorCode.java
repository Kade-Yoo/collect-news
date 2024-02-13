package com.example.domain.common;

public enum ErrorCode {

    INVALID_INPUT_VALUE_ERROR("입력 값이 유효하지 않습니다."),
    EXISTS_KEYWORD("이미 존재하는 키워드입니다."),
    UNAUTHORIZED_ERROR("허가되지 않았습니다."),
    AUTHORIZATION_EXPIRED_ERROR("토큰이 만료 되었습니다."),
    ALREADY_PRESENT_SITE_ERROR("이미 존재하는 사이트입니다."),
    ;

    public String getMessage() {
        return message;
    }

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }
}