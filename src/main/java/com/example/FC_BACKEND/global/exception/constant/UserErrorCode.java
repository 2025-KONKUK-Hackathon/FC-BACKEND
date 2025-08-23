package com.example.FC_BACKEND.global.exception.constant;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public enum UserErrorCode implements ErrorCode {
    EMAIL_SEND_FAILED(HttpStatus.INTERNAL_SERVER_ERROR.value(),"이메일을 전송할 수 없습니다.")

    ;
    private final int httpStatus;
    private final String message;

    @Override
    public int getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
