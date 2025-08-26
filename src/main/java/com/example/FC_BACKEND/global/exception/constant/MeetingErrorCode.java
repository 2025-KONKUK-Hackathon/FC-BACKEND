package com.example.FC_BACKEND.global.exception.constant;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public enum MeetingErrorCode implements ErrorCode {
    INVALID_ACTUAL_DATE(HttpStatus.BAD_REQUEST.value(), "활동 기간이 이미 지났습니다."),
    INVALID_DATE(HttpStatus.BAD_REQUEST.value(), "시작 기간이 종료 기간보다 과거여야 합니다.")
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
