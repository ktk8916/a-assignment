package com.artinus.api.global.exception;

import com.artinus.api.global.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ArtinusRestControllerAdvice {
    
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse<?>> handleRuntimeException(RuntimeException e){
        log.error("RuntimeException : ", e);
        ApiResponse<?> response = ApiResponse.fail(e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        ApiResponse<?> response = ApiResponse.fail(e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    @ExceptionHandler(ArtinusAppException.class)
    public ResponseEntity<ApiResponse<?>> handleApiException(ArtinusAppException e, HttpServletRequest request){
        ApiResponse<?> response = ApiResponse.fail(e.getCode());
        return ResponseEntity.status(e.getHttpStatus())
                .body(response);
    }

    // TODO : 각종 handler.....
}
