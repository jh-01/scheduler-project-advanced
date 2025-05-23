package org.example.scheduleadvanced.exception;

import lombok.RequiredArgsConstructor;
import org.example.scheduleadvanced.dto.type.ErrorCode;

@RequiredArgsConstructor
public class CustomException extends RuntimeException{
    private final ErrorCode errorCode;

    public String getMessage() {
        return errorCode.getMessage();
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
