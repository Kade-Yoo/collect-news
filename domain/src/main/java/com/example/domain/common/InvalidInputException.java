package com.example.domain.common;

public class InvalidInputException extends BusinessException {
    public InvalidInputException(ErrorCode errorCode) {
        super(errorCode);
    }
}
