package com.example.domain.common;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    public final ErrorCode errorCode;

    public BusinessException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
