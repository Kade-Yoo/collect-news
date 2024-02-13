package com.example.domain.common;

public class AuthorizationExpiredException extends BusinessException {
    public AuthorizationExpiredException(ErrorCode errorCode) {
        super(errorCode);
    }
}
