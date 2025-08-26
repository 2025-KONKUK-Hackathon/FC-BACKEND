package com.example.FC_BACKEND.global.exception.constant;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public enum MeetingErrorCode implements ErrorCode {
    INVALID_ACTUAL_DATE(HttpStatus.BAD_REQUEST.value(), "활동 기간이 이미 지났습니다."),
    INVALID_DATE(HttpStatus.BAD_REQUEST.value(), "시작 기간이 종료 기간보다 과거여야 합니다."),
    MEETING_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "존재하지 않는 모임입니다."),
    FULL_RECRUIT(HttpStatus.BAD_REQUEST.value(), "모집 인원이 꽉찼습니다."),
    RECRUIT_FINISHED(HttpStatus.BAD_REQUEST.value(), "모집 기간이 종료되었습니다."),
    DUPLICATE_MEETING_MEMBER(HttpStatus.BAD_REQUEST.value(), "이미 신청한 모임입니다."),
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
