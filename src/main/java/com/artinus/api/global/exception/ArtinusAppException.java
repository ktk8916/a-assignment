package com.artinus.api.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ArtinusAppException extends RuntimeException {

    private final ExceptionCode code;

    public ArtinusAppException(ExceptionCode code) {
        super(code.getMessage());
        this.code = code;
    }

    public ArtinusAppException(ExceptionCode code, Throwable throwable) {
        super(code.getMessage(), throwable);
        this.code = code;
    }

    public HttpStatus getHttpStatus() {
        return this.code.getHttpStatus();
    }

    public String getMessage() {
        return super.getMessage();
    }
}
