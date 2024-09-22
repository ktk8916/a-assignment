package com.artinus.api.global;

import com.artinus.api.global.exception.ExceptionCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    private final boolean isSuccess;
    private final T result;
    private final String message;
    private final String code;
    private final LocalDateTime time = LocalDateTime.now();

    @Builder
    private ApiResponse(boolean isSuccess, T result, String message, String code) {
        this.isSuccess = isSuccess;
        this.result = result;
        this.message = message;
        this.code = code;
    }

    public static <T> ApiResponse<T> success(T result) {
        return ApiResponse.<T>builder()
                .isSuccess(true)
                .result(result)
                .build();
    }

    public static <T> ApiResponse<T> success() {
        return ApiResponse.<T>builder()
                .isSuccess(true)
                .build();
    }

    public static <T> ApiResponse<T> fail(ExceptionCode code) {
        return ApiResponse.<T>builder()
                .isSuccess(false)
                .message(code.getMessage())
                .code(code.name())
                .build();
    }
}
