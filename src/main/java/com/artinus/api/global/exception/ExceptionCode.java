package com.artinus.api.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionCode {

    NOT_FOUND_ENTITY(HttpStatus.NOT_FOUND, "not found entity"),
    INVALID_VALUE(HttpStatus.BAD_REQUEST, "invalid value"),
    INVALID_SUBSCRIBE_PLAN(HttpStatus.BAD_REQUEST, "invalid subscribe plan"),

    ;

    private final HttpStatus httpStatus;
    private final String message;
}
