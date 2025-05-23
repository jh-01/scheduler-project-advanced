package org.example.scheduleadvanced.dto;

import jakarta.validation.constraints.NotNull;
import org.example.scheduleadvanced.dto.type.ErrorCode;


public class ExceptionDto {
    @NotNull
    private final int code;

    @NotNull
    private final String message;

    public ExceptionDto(ErrorCode errorCode){
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    public static ExceptionDto of(ErrorCode errorCode){
        return new ExceptionDto(errorCode);
    }
}
