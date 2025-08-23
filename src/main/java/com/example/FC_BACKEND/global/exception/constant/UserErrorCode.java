package com.example.FC_BACKEND.global.exception.constant;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public enum UserErrorCode implements ErrorCode {
    EMAIL_SEND_FAILED(HttpStatus.INTERNAL_SERVER_ERROR.value(),"이메일을 전송할 수 없습니다."),
    EMAIL_DUPLICATE(HttpStatus.BAD_REQUEST.value(), "이미 존재하는 이메일입니다."),
    INVALID_EMAIL_TYPE(HttpStatus.BAD_REQUEST.value(), "이메일 형식으로 작성해주세요"),
    INVALID_EMAIL(HttpStatus.BAD_REQUEST.value(), "건국대학교 학생만 이용 가능합니다."),
    VERIFY_CODE_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "인증번호가 존재하지 않습니다. 다시 요청해주세요."),
    VERIFY_CODE_MISMATCH(HttpStatus.BAD_REQUEST.value(), "인증번호가 일치하지 않습니다."),
    NOT_VERIFIED_EMAIL(HttpStatus.BAD_REQUEST.value(), "이메일 인증이 완료되지 않은 사용자입니다."),

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
