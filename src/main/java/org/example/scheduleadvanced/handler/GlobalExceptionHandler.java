package org.example.scheduleadvanced.handler;

import lombok.extern.slf4j.Slf4j;
import org.example.scheduleadvanced.dto.ApiResponse;
import org.example.scheduleadvanced.dto.type.ErrorCode;
import org.example.scheduleadvanced.exception.CustomException;
import org.example.scheduleadvanced.exception.LoginFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {LoginFailedException.class})
    public ResponseEntity<String> handleLoginFailed(LoginFailedException e){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }

    @ExceptionHandler(value = {CustomException.class})
    public ApiResponse<?> handleCostomException(CustomException e){
        log.error("handleCustomException() in GlobalExceptionHandler throw CustomException : {}", e.getMessage());
        return ApiResponse.fail(e);
    }


    // 기본 예외
    @ExceptionHandler(value = {Exception.class})
    public ApiResponse<?> handleException(Exception e) {
        log.error("handleException() in GlobalExceptionHandler throw Exception : {}", e.getMessage());
        e.printStackTrace();
        return ApiResponse.fail(new CustomException(ErrorCode.INTERNAL_SERVER_ERROR));
    }
}
