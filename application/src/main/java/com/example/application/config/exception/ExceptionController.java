package com.example.application.config.exception;

import com.example.domain.common.BusinessException;
import com.example.domain.common.ErrorCode;
import com.example.domain.common.InvalidInputException;
import com.example.domain.common.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
class ExceptionController {
    @ExceptionHandler(UnauthorizedException.class)
    public ErrorResponse unauthorizedException(UnauthorizedException e) {
        return ErrorResponse.create(e, HttpStatus.UNAUTHORIZED, ErrorCode.UNAUTHORIZED_ERROR.getMessage());
    }

    @ExceptionHandler(InvalidInputException.class)
    public ErrorResponse unauthorizedException(InvalidInputException e) {
        return ErrorResponse.create(e, HttpStatus.BAD_REQUEST, e.getErrorCode().getMessage());
    }

    @ExceptionHandler(BusinessException.class)
    public ErrorResponse businessException(BusinessException e) {
        return ErrorResponse.create(e, HttpStatus.UNPROCESSABLE_ENTITY, e.getErrorCode().getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ErrorResponse unauthorizedException(RuntimeException e) {
       return ErrorResponse.create(e, HttpStatus.NOT_FOUND, e.getMessage());
    }
}