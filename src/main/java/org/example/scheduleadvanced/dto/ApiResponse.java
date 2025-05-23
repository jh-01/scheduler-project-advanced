package org.example.scheduleadvanced.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import org.example.scheduleadvanced.exception.CustomException;
import org.springframework.http.HttpStatus;

public record ApiResponse<T>(
    @JsonIgnore
    HttpStatus httpStatus,
    boolean success,
    @Nullable T data,
    @Nullable ExceptionDto error,
    @Nullable String message
    ) {
    public static <T> ApiResponse<T> ok(@Nullable final T data, @Nullable String message) {
        return new ApiResponse<>(HttpStatus.OK, true, data, null, message);
    }

    public static <T> ApiResponse<T> created(@Nullable final T data, @Nullable String message) {
        return new ApiResponse<>(HttpStatus.CREATED, true, data, null, message);
    }

    public static <T> ApiResponse<T> fail(final CustomException e) {
        return new ApiResponse<>(e.getErrorCode().getHttpStatus(), false, null, ExceptionDto.of(e.getErrorCode()), e.getMessage());
    }
}
